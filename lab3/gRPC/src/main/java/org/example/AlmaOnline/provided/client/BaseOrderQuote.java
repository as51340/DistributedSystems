package org.example.AlmaOnline.provided.client;

import java.util.List;

public abstract class BaseOrderQuote {
    private final String restaurantId;
    private final String orderId;
    private final String customer;
    private final List<String> items;

    protected BaseOrderQuote(String restaurantId, String orderId, String customer, List<String> items) {
        this.restaurantId = restaurantId;
        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomer() {
        return customer;
    }

    public List<String> getItems() {
        return items;
    }
}
