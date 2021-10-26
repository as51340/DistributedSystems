package org.example.AlmaOnline.provided.service;

import org.example.AlmaOnline.provided.service.exceptions.OrderException;

import java.util.*;

public class AlmaOnlineService {
    private final Map<String, Restaurant> restaurants;

    public AlmaOnlineService(List<Restaurant> restaurants) {
        this.restaurants = new HashMap<>();
        restaurants.forEach(restaurant -> {
            this.restaurants.put(restaurant.getId(), restaurant);
        });
    }

    public Collection<Restaurant> getRestaurants() {
        return this.restaurants.values();
    }

    public Optional<Restaurant> getRestaurant(String id) {
        return Optional.ofNullable(restaurants.get(id));
    }

    public Optional<Menu> getRestaurantMenu(String restaurantId) {
        return getRestaurant(restaurantId).map(Restaurant::getMenu);
    }

    public void createDineInOrder(String restaurantId, DineInOrderQuote quote) throws OrderException {
        if (!this.restaurants.containsKey(restaurantId)) {
            throw new OrderException("cannot order at unknown restaurant");
        }

        Restaurant restaurant = this.restaurants.get(restaurantId);
        restaurant.createDineInOrder(quote);
    }

    public void createDeliveryOrder(String restaurantId, DeliveryOrderQuote quote) throws OrderException {
        if (!this.restaurants.containsKey(restaurantId)) {
            throw new OrderException("cannot order at unknown restaurant");
        }

        Restaurant restaurant = this.restaurants.get(restaurantId);
        restaurant.createDeliveryOrder(quote);
    }

    public Optional<Order> getOrder(String restaurantId, String orderId) {
        if (!this.restaurants.containsKey(restaurantId)) {
            return Optional.empty();
        }

        Restaurant restaurant = this.restaurants.get(restaurantId);
        return restaurant.getOrder(orderId);
    }
}
