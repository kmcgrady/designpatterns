package com.ga.designpatterns.models;

import java.util.List;

public class ItemPackage {
    private List<Item> items;

    public ItemPackage(List<Item> items) {
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

    public List<Item> getItems() {
        return this.items;
    }
}
