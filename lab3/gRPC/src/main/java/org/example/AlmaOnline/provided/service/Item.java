package org.example.AlmaOnline.provided.service;

public class Item {
    private final String name;
    private final double price;

    public Item(String name, double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("price must be nonzero");
        }

        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }
}
