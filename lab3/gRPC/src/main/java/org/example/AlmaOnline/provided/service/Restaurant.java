package org.example.AlmaOnline.provided.service;

import org.example.AlmaOnline.provided.service.exceptions.OrderException;

import java.util.*;
import java.util.stream.Collectors;

public class Restaurant {
    private final String id;
    private final String name;
    private final Menu menu;
    private final Map<String, Order> orders = new HashMap<>();

    public Restaurant(String id, String name, Menu menu) {
        this.id = id;
        this.name = name;
        this.menu = menu;
    }

    public String getId() {
        return id;
    }

    public String getName() { return name; }

    public Menu getMenu() {
        return menu;
    }

    public void createDineInOrder(DineInOrderQuote quote) throws OrderException {
        List<Item> items = prepareOrder(quote);
        DineInOrder order = new DineInOrder(quote.getId(), quote.getCreationDate(), quote.getCustomer(), items, quote.getReservationDate());
        this.orders.put(order.getId(), order);
    }

    public void createDeliveryOrder(DeliveryOrderQuote quote) throws OrderException {
        List<Item> items = prepareOrder(quote);
        DeliveryOrder order = new DeliveryOrder(quote.getId(), quote.getCreationDate(), quote.getCustomer(), items, quote.getDeliveryAddress());
        this.orders.put(order.getId(), order);
    }

    private List<Item> prepareOrder(OrderQuote quote) throws OrderException {
        if (this.orders.containsKey(quote.getId())) {
            throw new OrderException("order already exists");
        }

        var unknowns = quote.getLineItems().stream()
            .filter(item -> !this.getMenu().hasItem(item))
            .collect(Collectors.toList());

        if (unknowns.size() != 0) {
            throw new OrderException("cannot create order with unknown items: " + unknowns);
        }

        var items = quote.getLineItems().stream()
            .map(item -> this.getMenu().getItem(item))
            .collect(Collectors.toList());
        return items;
    }

    public Collection<Order> getOrders() {
        return orders.values();
    }

    public Optional<Order> getOrder(String id) {
        return Optional.ofNullable(this.orders.get(id));
    }
}
