package br.com.lolfood.service.impl;

import br.com.lolfood.dao.OrderDao;
import br.com.lolfood.model.Client;
import br.com.lolfood.model.Order;
import br.com.lolfood.model.OrderStatus;
import br.com.lolfood.model.Restaurant;
import br.com.lolfood.service.OrderService;
import br.com.lolfood.util.CoordinatesUtil;
import jakarta.inject.Inject;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_INSTANT;

public class OderServiceImpl implements OrderService {

    @Inject
    private OrderDao<Order> orderDao;

    @Override
    public void createOrder(Order order) {

        setDueTime(order);
        order.setStatus(OrderStatus.RECEIVING);
        orderDao.salvar(order);
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
