package com.cinema.controller;

import com.cinema.model.MovieSession;
import com.cinema.model.ShoppingCart;
import com.cinema.model.User;
import com.cinema.model.dto.ShoppingCartResponseDto;
import com.cinema.service.MapperToDto;
import com.cinema.service.MovieSessionService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final MovieSessionService movieSessionService;
    private final MapperToDto<ShoppingCart, ShoppingCartResponseDto> mapperToDto;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  MovieSessionService movieSessionService, MapperToDto<ShoppingCart,
            ShoppingCartResponseDto> mapperToDto) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
        this.mapperToDto = mapperToDto;
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(@RequestParam Long userId, @RequestParam Long movieSessionId) {
        User user = userService.get(userId);
        MovieSession movieSession = movieSessionService.get(movieSessionId);
        shoppingCartService.addSession(movieSession, user);
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(@RequestParam Long userId) {
        User user = userService.get(userId);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        return mapperToDto.mapToDto(shoppingCart);
    }
}
