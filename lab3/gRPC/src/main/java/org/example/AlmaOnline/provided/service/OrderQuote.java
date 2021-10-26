package org.example.AlmaOnline.provided.service;

import java.util.Date;
import java.util.List;

public class OrderQuote {
    private final String id;
    private final Date creationDate;
    private final String customer;
    private final List<String> lineItems;

    public OrderQuote(String id, Date creationDate, String customer, List<String> lineItems) {
        this.id = id;
        this.creationDate = creationDate;
        this.customer = customer;
        this.lineItems = lineItems;
    }

    public String getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getCustomer() {
        return customer;
    }

    public List<String> getLineItems() {
        return lineItems;
    }
}
