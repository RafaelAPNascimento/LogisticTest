package br.com.lolfood.util;

import br.com.lolfood.model.Client;
import br.com.lolfood.model.Order;
import br.com.lolfood.model.Restaurant;

public class TestUtil {

    private static Restaurant restaurant = Restaurant.builder().id(5L).lat(-38.9).lon(81.63).build();
    private static Client client = Client.builder().id(5L).lat(-38.9).lon(81.63).build();
    private static Order order = Order.builder().id(2L).restaurant(3L).client(4L).build();

    public static Restaurant getRestaurant() {
        return restaurant;
    }

    public static Client getClient() {
        return client;
    }

    public static Order getOrder() {
        return order;
    }
}
