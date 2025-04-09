package com.bookingtrips.booking_trips_backend.mapper;

import com.bookingtrips.booking_trips_backend.dto.UserDto;
import com.bookingtrips.booking_trips_backend.dto.request.UserUpdateRequest;
import com.bookingtrips.booking_trips_backend.entity.User;
import com.bookingtrips.booking_trips_backend.dto.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements UpdateGenericMapper<User, UserDto, UserRequest, UserUpdateRequest>{
    private final ModelMapper mapper;
    @Override
    public UserDto toDto(User entity) {
        return mapper.map(entity, UserDto.class);
    }

    @Override
    public User toEntity(UserRequest request) {
        return mapper.map(request, User.class);
    }

    @Override
    public void toEntity(UserUpdateRequest updateRequest, User entity) {
        mapper.map(updateRequest,entity);
    }
}
