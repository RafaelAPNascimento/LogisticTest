package br.com.lolfood.service.impl;

import br.com.lolfood.model.Client;
import br.com.lolfood.model.Order;
import br.com.lolfood.model.Restaurant;
import br.com.lolfood.model.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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

    public static Order insertOrder(Order order) {

        if (orders.containsKey(order.getId()))
            throw new RuntimeException(String.format("Order with id %s already exists", order.getId()));

        return orders.put(order.getId(), order);
    }

    public static Order getOrder(Object id) {
        return orders.get(id);
    }

    public static Order updateOrder(Order order) {

        if (!orders.containsKey(order.getId()))
            throw new RuntimeException(String.format("Unimpossible to update, Order id %s doesn't exist yet", order.getId()));

        return orders.put(order.getId(), order);
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

    public static void clear() {
        restaurants.clear();
        orders.clear();
        clients.clear();
        routes.clear();
    }

    public static List<Order> getOrdersByRestaurant(Long id) {

        return
            orders.values()
                .stream()
                .filter(order -> order.getRestaurant() == id)
                .collect(toList());
    }
}
