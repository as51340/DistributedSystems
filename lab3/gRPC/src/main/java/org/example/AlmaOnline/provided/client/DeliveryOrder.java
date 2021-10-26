package org.example.AlmaOnline.provided.client;

import java.util.List;

public class DeliveryOrder extends BaseOrderQuote {

    private final String deliveryAddress;

    public DeliveryOrder(String restaurantId, String orderId, String customer, List<String> items, String deliveryAddress) {
        super(restaurantId, orderId, customer, items);

        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    @Override
    public String toString() {
        return "DeliveryOrder{" +
            "restaurantId='" + getRestaurantId() + '\'' +
            ", orderId='" + getOrderId() + '\'' +
            ", customer='" + getCustomer() + '\'' +
            ", items=" + getItems() +
            ", deliveryAddress=" + getDeliveryAddress() +
            '}';
    }
}
