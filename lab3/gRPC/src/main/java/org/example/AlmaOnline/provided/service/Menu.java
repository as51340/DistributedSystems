package org.example.AlmaOnline.provided.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private final Map<String, Item> items = new HashMap<>();

    public Menu() { }

    public Collection<Item> getItems() {
        return this.items.values();
    }

    public Menu addItem(Item item) {
        this.items.put(item.getName(), item);
        return this;
    }

    Boolean hasItem(Item item) {
        return this.items.containsKey(item.getName());
    }

    Boolean hasItem(String itemName) {
        return this.items.containsKey(itemName);
    }

    Item getItem(String itemName) {
        return this.items.get(itemName);
    }
}
