package br.com.lolfood.dao.impl;

import br.com.lolfood.dao.OrderDao;
import br.com.lolfood.model.Order;
import br.com.lolfood.service.impl.Database;

import java.util.List;

public class OrderDaoImpl implements OrderDao<Order> {

    @Override
    public Order getById(Object id) {
        return Database.getOrder(id);
    }

    @Override
    public Order save(Order entity) {
        return Database.insertOrder(entity);
    }

    @Override
    public Order update(Order entity) {
        return Database.updateOrder(entity);
    }

    @Override
    public List<Order> getByRestaurant(Long id) {
        return Database.getOrdersByRestaurant(id);
    }

    @Override
    public Order delete(Order entity) {
        return null;
    }

    @Override
    public Class<Order> getEntityClass() {
        return null;
    }
}
