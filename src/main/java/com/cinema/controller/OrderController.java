package com.cinema.controller;

import com.cinema.model.Order;
import com.cinema.model.User;
import com.cinema.model.dto.OrderResponseDto;
import com.cinema.service.MapperToDto;
import com.cinema.service.OrderService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;
    private final MapperToDto<Order, OrderResponseDto> mapperToDto;

    @Autowired
    public OrderController(UserService userService, ShoppingCartService shoppingCartService,
                           OrderService orderService, MapperToDto<Order,
            OrderResponseDto> mapperToDto) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
        this.mapperToDto = mapperToDto;
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestParam Long userId) {
        User user = userService.get(userId);
        orderService.completeOrder(shoppingCartService.getByUser(user));
    }

    @GetMapping
    public List<OrderResponseDto> getOrdersHistoryByUser(@RequestParam Long userId) {
        User user = userService.get(userId);
        List<Order> ordersHistory = orderService.getOrdersHistory(user);
        return ordersHistory.stream()
                .map(mapperToDto::mapToDto)
                .collect(Collectors.toList());
    }
}
