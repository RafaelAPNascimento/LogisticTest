package br.com.lolfood.service.impl;

import br.com.lolfood.model.Client;
import br.com.lolfood.model.Order;
import br.com.lolfood.model.Restaurant;
import br.com.lolfood.model.Route;

import java.util.HashMap;
import java.util.Map;

public final class Database {

    private static Map<Long, Restaurant> restaurants;
    private static Map<Long, Client> clients;
    private static Map<Long, Order> orders;
    private static Map<Long, Route> routes;

    static {
        restaurants = new HashMap<>();
        clients = new HashMap<>();
        orders = new HashMap<>();
        routes = new HashMap<>();
    }

    public static Restaurant insertRestaurant(Restaurant restaurant) {

        if (restaurants.containsKey(restaurant.getId()))
            throw new RuntimeException(String.format("Restaurant with id %s already exists", restaurant.getId()));

        return restaurants.put(restaurant.getId(), restaurant);
    }

    public static Restaurant updateRestaurant(Restaurant restaurant) {

        if (!restaurants.containsKey(restaurant.getId()))
            throw new RuntimeException(String.format("Unimpossible to update, Restaurant id %s doesn't exist yet", restaurant.getId()));

        return restaurants.put(restaurant.getId(), restaurant);
    }

    public static Restaurant deleteRestaurant(Restaurant restaurant) {
        return restaurants.remove(restaurant.getId());
    }

    public static Restaurant getRestaurant(Object id) {
        return restaurants.get(id);
    }

    public static Client insertClient(Client client) {

        if (clients.containsKey(client.getId()))
            throw new RuntimeException(String.format("Client with id %s already exists", client.getId()));

        return clients.put(client.getId(), client);
    }

    public static Client updateClient(Client client) {

        if (!clients.containsKey(client.getId()))
            throw new RuntimeException(String.format("Unimpossible to update, Client id %s doesn't exist yet", client.getId()));

        return clients.put(client.getId(), client);
    }

    public static Client deleteClient(Object id) {
        return clients.remove(id);
    }

    public static Client getClient(Object id) {
        return clients.get(id);
    }
}
