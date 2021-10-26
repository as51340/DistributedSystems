package org.example.AlmaOnline.provided.client;

import java.util.Objects;

public class ItemInfo {
    private final String name;
    private final double price;

    public ItemInfo(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ItemInfo{" +
            "name='" + name + '\'' +
            ", price=" + price +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemInfo itemInfo = (ItemInfo) o;
        return Double.compare(itemInfo.price, price) == 0 && name.equals(itemInfo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
