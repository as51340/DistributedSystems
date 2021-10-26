package org.example.AlmaOnline.provided.service;

import java.util.Date;
import java.util.List;

public class DineInOrderQuote extends OrderQuote{
    public final Date reservationDate;

    public DineInOrderQuote(String id, Date date, String customer, List<String> lineItems, Date reservationDate) {
        super(id, date, customer, lineItems);

        this.reservationDate = reservationDate;
    }

    public Date getReservationDate() {
        return reservationDate;
    }
}
