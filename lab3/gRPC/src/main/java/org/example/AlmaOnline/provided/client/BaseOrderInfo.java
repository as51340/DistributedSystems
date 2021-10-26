package org.example.AlmaOnline.provided.client;

import java.util.Date;
import java.util.List;

public class BaseOrderInfo {
    private final String customer;
    private final Date createDate;
    private final List<ItemInfo> items;


    public BaseOrderInfo(String customer, Date createDate, List<ItemInfo> items) {
        this.customer = customer;
        this.createDate = createDate;
        this.items = items;
    }

    public String getCustomer() {
        return customer;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public List<ItemInfo> getItems() {
        return items;
    }
}
