package org.example.AlmaOnline.provided.client;

import java.util.Date;
import java.util.List;

public class DineInOrderInfo extends BaseOrderInfo {
    private final Date reservationDate;

    public DineInOrderInfo(String customer, Date createDate, List<ItemInfo> items, Date reservationDate) {
        super(customer, createDate, items);
        this.reservationDate = reservationDate;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
            "customer='" + getCustomer() + '\'' +
            ", createDate=" + getCreateDate() +
            ", items=" + getItems() +
            ", reservationDate=" + getReservationDate() +
            '}';
    }
}
