package br.com.lolfood.util;

import br.com.lolfood.model.Restaurant;

public class TestUtil {

    private static Restaurant restaurant = Restaurant.builder().id(5L).lat(-38.9).lon(81.63).build();

    public static Restaurant getRestaurantObject() {
        return restaurant;
    }
}
