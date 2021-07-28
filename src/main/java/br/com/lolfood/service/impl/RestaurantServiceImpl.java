package br.com.lolfood.service.impl;

import br.com.lolfood.dao.RestaurantDao;
import br.com.lolfood.exception.BusinessException;
import br.com.lolfood.model.Restaurant;
import br.com.lolfood.service.RestaurantService;
import jakarta.inject.Inject;

public class RestaurantServiceImpl implements RestaurantService {

    @Inject
    private RestaurantDao<Restaurant> dao;

    @Override
    public void create(Restaurant restaurant) {
        dao.save(restaurant);
    }

    @Override
    public void update(Restaurant restaurant) {
        try {
            dao.update(restaurant);
        }
        catch (RuntimeException e) {
            throw new BusinessException(e.getMessage(), 404);
        }
    }

    @Override
    public Restaurant find(Long id) {
        return dao.getById(id);
    }
}
