package org.example.AlmaOnline.provided.service;

import java.util.Date;
import java.util.List;

public class DineInOrder extends Order {

    private final Date reservationDate;

    DineInOrder(String id, Date creationDate, String customer, List<Item> items, Date reservationDate) {
        super(id, creationDate, customer, items);

        this.reservationDate = reservationDate;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    @Override
    public String toString() {
        return "DineInOrder{" +
            "id='" + getId() + '\'' +
            ", creationDate=" + getCreationDate() +
            ", customer='" + getCustomer() + '\'' +
            ", items=" + getItems() +
            "reservationDate=" + getReservationDate() +
            '}';
    }
}
