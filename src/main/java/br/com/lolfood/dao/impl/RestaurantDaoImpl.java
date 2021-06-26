package br.com.lolfood.dao.impl;

import br.com.lolfood.dao.RestaurantDao;
import br.com.lolfood.model.Restaurant;
import br.com.lolfood.service.impl.Database;

public class RestaurantDaoImpl implements RestaurantDao<Restaurant> {


    @Override
    public Restaurant getById(Object id) {
        return Database.getRestaurant(id);
    }

    @Override
    public Restaurant salvar(Restaurant entity) {
        return Database.insertRestaurant(entity);
    }

    @Override
    public Restaurant update(Restaurant entity) {
        return Database.updateRestaurant(entity);
    }

    @Override
    public Restaurant delete(Restaurant restaurant) {
        return Database.deleteRestaurant(restaurant);
    }

    @Override
    public Class<Restaurant> getEntityClass() {
        return Restaurant.class;
    }
}
