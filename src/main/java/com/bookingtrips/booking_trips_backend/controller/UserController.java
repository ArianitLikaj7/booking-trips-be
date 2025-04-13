package com.bookingtrips.booking_trips_backend.controller;

import com.bookingtrips.booking_trips_backend.dto.UserDto;
import com.bookingtrips.booking_trips_backend.dto.request.UserRequest;
import com.bookingtrips.booking_trips_backend.dto.request.UserUpdateRequest;
import com.bookingtrips.booking_trips_backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<UserDto> create(@ModelAttribute @Valid UserRequest request) {
        return new ResponseEntity<>(userService.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable UUID id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>>getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable UUID id,
            @ModelAttribute @Valid UserUpdateRequest updateRequest
    ) {
        return ResponseEntity.ok(userService.update(id, updateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> sendPasswordResetEmail(@RequestParam String email) {
        userService.sendPasswordResetEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestParam UUID userId, @RequestParam String oldPassword, @RequestParam String newPassword) {
        userService.changePassword(userId, oldPassword, newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
