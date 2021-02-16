package com.cinema.model.dto;

import java.util.List;

public class ShoppingCartResponseDto {
    private Long usersShoppingCartId;
    private List<Long> ticketsId;

    public Long getUsersShoppingCartId() {
        return usersShoppingCartId;
    }

    public void setUsersShoppingCartId(Long usersShoppingCartId) {
        this.usersShoppingCartId = usersShoppingCartId;
    }

    public List<Long> getTicketsId() {
        return ticketsId;
    }

    public void setTicketsId(List<Long> ticketsId) {
        this.ticketsId = ticketsId;
    }
}
