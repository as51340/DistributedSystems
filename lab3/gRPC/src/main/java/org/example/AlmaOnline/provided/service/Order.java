package org.example.AlmaOnline.provided.service;

import java.util.*;

public abstract class Order {
    private final String id;
    private final Date creationDate;
    private final String customer;
    private final Map<String, Item> items = new HashMap<>();


    Order(String id, Date creationDate, String customer, List<Item> items) {
        this.id = id;
        this.creationDate = creationDate;
        this.customer = customer;
        items.forEach(item -> {
            this.items.put(item.getName(), item);
        });
    }

    public String getId() {
        return this.id;
    }

    public String getCustomer() {
        return this.customer;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public Collection<Item> getItems() {
        return this.items.values();
    }

    public double getTotal() {
        return this.getItems()
            .stream()
            .map(Item::getPrice)
            .reduce(Double::sum)
            .orElse(0.0);
    }
}
