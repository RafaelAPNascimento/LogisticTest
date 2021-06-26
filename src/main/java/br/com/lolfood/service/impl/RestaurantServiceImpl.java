package br.com.lolfood.service.impl;

import br.com.lolfood.dao.RestaurantDao;
import br.com.lolfood.model.Restaurant;
import br.com.lolfood.service.RestaurantService;

public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantDao dao;

    @Override
    public void create(Restaurant restaurant) {
        dao.salvar(restaurant);
    }
}
