package org.example.AlmaOnline.provided.client;

import java.util.Map;

public class MenuInfo {
    private final Map<String, Double> items;

    public MenuInfo(Map<String, Double> items) {
        this.items = items;
    }

    public Map<String, Double> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "MenuInfo{" +
            "items=" + items +
            '}';
    }
}
