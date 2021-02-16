package com.cinema.service.mapper;

import com.cinema.model.Order;
import com.cinema.model.Ticket;
import com.cinema.model.dto.OrderResponseDto;
import com.cinema.service.MapperToDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements MapperToDto<Order, OrderResponseDto> {

    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setUserId(order.getUser().getId());
        orderResponseDto.setOrderTime(order.getOrderTime().toString());
        List<Long> ticketsId = order.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        orderResponseDto.setTicketsId(ticketsId);
        return orderResponseDto;
    }
}
