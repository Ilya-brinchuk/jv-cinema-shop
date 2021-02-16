package com.cinema.service.mapper;

import com.cinema.model.ShoppingCart;
import com.cinema.model.Ticket;
import com.cinema.model.dto.ShoppingCartResponseDto;
import com.cinema.service.MapperToDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper implements MapperToDto<ShoppingCart, ShoppingCartResponseDto> {
    @Override
    public ShoppingCartResponseDto mapToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setUsersShoppingCartId(shoppingCart.getId());
        shoppingCartResponseDto.setTicketsId(shoppingCart.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return shoppingCartResponseDto;
    }
}
