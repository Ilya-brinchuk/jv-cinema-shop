package com.cinema.controller;

import com.cinema.exception.DataProcessingException;
import com.cinema.model.User;
import com.cinema.model.dto.UserResponseDto;
import com.cinema.service.MapperToDto;
import com.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final MapperToDto<User, UserResponseDto> mapperToDto;

    @Autowired
    public UserController(UserService userService, MapperToDto<User, UserResponseDto> mapperToDto) {
        this.userService = userService;
        this.mapperToDto = mapperToDto;

    }

    @GetMapping("/by-email")
    public UserResponseDto getUserByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email).orElseThrow(() ->
                new DataProcessingException("Can't find user by this email: " + email));
        return mapperToDto.mapToDto(user);
    }
}
