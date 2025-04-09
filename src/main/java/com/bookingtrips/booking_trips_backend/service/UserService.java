package com.bookingtrips.booking_trips_backend.service;

import com.bookingtrips.booking_trips_backend.dao.UserRepository;
import com.bookingtrips.booking_trips_backend.dto.UserDto;
import com.bookingtrips.booking_trips_backend.dto.request.UserRequest;
import com.bookingtrips.booking_trips_backend.dto.request.UserUpdateRequest;
import com.bookingtrips.booking_trips_backend.entity.User;
import com.bookingtrips.booking_trips_backend.exception.ResourceNotFoundException;
import com.bookingtrips.booking_trips_backend.exception.UserAlreadyExists;
import com.bookingtrips.booking_trips_backend.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;
    private final ImageUploadService imageUploadService;

    @Transactional
    public UserDto create(UserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExists("Username already exists: " + request.getUsername());
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExists("Email already exists: " + request.getEmail());
        }

        User user = userMapper.toEntity(request);
        setUserPasswordAndRole(request, user);

        if (request.getImage() != null && !request.getImage().isEmpty()) {
            try {
                String imageUrlOfUser = imageUploadService.uploadFile(request.getImage());
                user.setImageUrlOfUser(imageUrlOfUser);
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload profile image", e);
            }
        }

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public UserDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        return userMapper.toDto(user);
    }

    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDto update(Long id, UserUpdateRequest request) {
        User userInDb = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));

        userMapper.toEntity(request, userInDb);

        if (request.getImage() != null && !request.getImage().isEmpty()) {
            try {
                String imageUrlOfUser = imageUploadService.uploadFile(request.getImage());
                userInDb.setImageUrlOfUser(imageUrlOfUser);
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload profile image", e);
            }
        }

        User updatedUser = userRepository.save(userInDb);
        return userMapper.toDto(updatedUser);
    }

    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = (User) authentication.getPrincipal();
        return userMapper.toDto(loggedUser);
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        userRepository.delete(user);
    }

    @Transactional
    public void sendPasswordResetEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found"));

        String newPassword = generateRandomPassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Password Reset Request");
            mailMessage.setText("Your new password is: " + newPassword);
            javaMailSender.send(mailMessage);
        } catch (MailException e) {
            throw new RuntimeException("Failed to send password reset email", e);
        }
    }

    @Transactional
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    private void setUserPasswordAndRole(UserRequest request, User user) {
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
    }

    private String generateRandomPassword() {
        return RandomStringUtils.randomAlphanumeric(12);
    }
}
