package com.bookingtrips.booking_trips_backend.config;

import com.bookingtrips.booking_trips_backend.dao.UserRepository;
import com.bookingtrips.booking_trips_backend.entity.Role;
import com.bookingtrips.booking_trips_backend.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(request);
        String email = oAuth2User.getAttribute("email");

        User user = userRepository.findByUsername(email)
                .orElseGet(() -> userRepository.save(User.builder()
                        .username(email)
                        .password("loginwithgoogle")
                        .role(Role.USER)
                        .build()));

        return new DefaultOAuth2User(
                List.of(new SimpleGrantedAuthority(user.getRole().name())),
                oAuth2User.getAttributes(),
                "email"
        );
    }
}