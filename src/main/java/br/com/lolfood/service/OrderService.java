package br.com.lolfood.service;

import br.com.lolfood.model.Order;

import java.util.List;

public interface OrderService {

    void createOrder(Order order);

    void updateOrder(String orderId);

    List<Order> getOrdersByRestaurant(Long id);

    Order getOrder(Long id);
}
