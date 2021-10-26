package org.example.AlmaOnline.provided.service;

import java.util.Date;
import java.util.List;

public class DeliveryOrderQuote extends OrderQuote {
    public final String deliveryAddress;

    public DeliveryOrderQuote(String id, Date date, String customer, List<String> lineItems, String deliveryAddress) {
        super(id, date, customer, lineItems);

        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }
}
