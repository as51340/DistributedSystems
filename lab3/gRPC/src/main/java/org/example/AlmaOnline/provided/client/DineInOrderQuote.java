package org.example.AlmaOnline.provided.client;

import java.util.Date;
import java.util.List;

public class DineInOrderQuote extends BaseOrderQuote {
    private final Date reservationDate;

    public DineInOrderQuote(String restaurantId, String orderId, String customer, List<String> items, Date reservationDate) {
       super(restaurantId, orderId, customer, items);

       this.reservationDate = reservationDate;

    }

    public Date getReservationDate() {
        return reservationDate;
    }

    @Override
    public String toString() {
        return "DineInOrder{" +
            "restaurantId='" + getRestaurantId() + '\'' +
            ", orderId='" + getOrderId() + '\'' +
            ", customer='" + getCustomer() + '\'' +
            ", items=" + getItems() +
            ", date=" + getReservationDate() +
            '}';
    }
}
