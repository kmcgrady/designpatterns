package com.ga.designpatterns.models;

import java.util.Collection;

public class ItemPackage {
    private Collection<Item> items;

    private ItemPackage() {}
    public ItemPackage(Collection<Item> items) {
        this.items = items;
    }

    public int getTotalCost() {
        int cost = 0;
        for (Item item : this.getItems()) {
            cost += item.getCost();
        }

        return cost;
    }

    public int getTotalValue() {
        int cost = 0;
        for (Item item : this.getItems()) {
            cost += item.getValue();
        }

        return cost;
    }

    public Collection<Item> getItems() {
        return this.items;
    }
}
