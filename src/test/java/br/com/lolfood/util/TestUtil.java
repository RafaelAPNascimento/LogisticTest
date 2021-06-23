package br.com.lolfood.util;

import br.com.lolfood.model.Client;
import br.com.lolfood.model.Restaurant;

public class TestUtil {

    private static Restaurant restaurant = Restaurant.builder().id(5L).lat(-38.9).lon(81.63).build();
    private static Client client = Client.builder().id(5L).lat(-38.9).lon(81.63).build();

    public static Restaurant getRestaurant() {
        return restaurant;
    }

    public static Client getClient() {
        return client;
    }
}
