package com.cinema.service;

import com.cinema.model.Order;
import com.cinema.model.ShoppingCart;
import com.cinema.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
