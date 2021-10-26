package org.example.AlmaOnline.provided.client;

import java.util.Date;
import java.util.List;

public class DeliveryOrderInfo extends BaseOrderInfo{

    private final String deliveryAddress;

    public DeliveryOrderInfo(String customer, Date createDate, List<ItemInfo> items, String deliveryAddress) {
        super(customer, createDate, items);

        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
            "customer='" + getCustomer() + '\'' +
            ", createDate=" + getCreateDate() +
            ", items=" + getItems() +
            ", deliveryAdddress" + getDeliveryAddress() +
            '}';
    }
}
