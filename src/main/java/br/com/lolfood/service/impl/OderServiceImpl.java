package br.com.lolfood.service.impl;

import br.com.lolfood.dao.OrderDao;
import br.com.lolfood.exception.BusinessException;
import br.com.lolfood.model.Client;
import br.com.lolfood.model.Order;
import br.com.lolfood.model.OrderStatus;
import br.com.lolfood.model.Restaurant;
import br.com.lolfood.service.OrderService;
import br.com.lolfood.util.CoordinatesUtil;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static br.com.lolfood.model.OrderStatus.*;
import static java.time.format.DateTimeFormatter.ISO_INSTANT;

public class OderServiceImpl implements OrderService {

    @Inject
    private OrderDao<Order> orderDao;

    @Override
    public void createOrder(Order order) {

        setDueTime(order);
        order.setStatus(RECEIVING);
        orderDao.save(order);
    }

    @Override
    public void updateOrder(String orderId) {

        Order order = orderDao.getById(orderId);

        if (Objects.isNull(order))
            throw new BusinessException(null, 404);

        if (!isOrderStatusValid(order))
            throw new BusinessException(null, 422);

        forwardStatus(order);
        orderDao.update(order);
    }

    private void forwardStatus(Order order) {

        if (order.getStatus() == RECEIVING)
            order.setStatus(PREPARING);

        else if (order.getStatus() == PREPARING)
            order.setStatus(READY);

        else if (order.getStatus() == PICKED)
            order.setStatus(DELIVERED);
    }

    private boolean isOrderStatusValid(Order order) {

        OrderStatus status = order.getStatus();
        return (status == RECEIVING || status == PREPARING || status == PICKED);
    }

    @Override
    public List<Order> getOrdersByRestaurant(Long id) {
        return orderDao.getByRestaurant(id);
    }

    @Override
    public Order getOrder(Long id) {
        return orderDao.getById(id);
    }

    private void setDueTime(Order order) {

        Client client = Database.getClient(order.getClient());
        Restaurant restaurant = Database.getRestaurant(order.getRestaurant());

        Double distance = CoordinatesUtil.calculateDistance(client, restaurant);

        LocalDateTime time = CoordinatesUtil.calculateDueTime(distance);

        String dueTime = time.format(ISO_INSTANT);
        order.setDueTime(dueTime);
    }
}
