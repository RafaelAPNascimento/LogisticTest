package br.com.lolfood.service;

import br.com.lolfood.model.Restaurant;

public interface RestaurantService {

    void create(Restaurant restaurant);

    void update(Restaurant restaurant);

    Restaurant find(Long id);
}
