package org.example.AlmaOnline.provided.service;

import java.util.Date;
import java.util.List;

public class DeliveryOrder extends Order {

    private final String deliveryAddress;

    DeliveryOrder(String id, Date creationDate, String customer, List<Item> items, String deliveryAddress) {
        super(id, creationDate, customer, items);

        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    @Override
    public String toString() {
        return "DeliveryOrder{" +
            "id='" + getId() + '\'' +
            ", creationDate=" + getCreationDate() +
            ", customer='" + getCustomer() + '\'' +
            ", items=" + getItems() +
            "deliveryAddress='" + getDeliveryAddress() + '\'' +
            '}';
    }
}
