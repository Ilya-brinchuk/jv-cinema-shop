package com.cinema.service.mapper;

import com.cinema.model.User;
import com.cinema.model.dto.UserRequestDto;
import com.cinema.model.dto.UserResponseDto;
import com.cinema.service.MapperToDto;
import com.cinema.service.MapperToEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements MapperToDto<User, UserResponseDto>,
        MapperToEntity<User, UserRequestDto> {
    @Override
    public UserResponseDto mapToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }

    @Override
    public User mapToEntity(UserRequestDto requestDto) {
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        return user;
    }
}
