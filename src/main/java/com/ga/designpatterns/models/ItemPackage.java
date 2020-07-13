package com.ga.designpatterns.models;

import java.util.List;

public class ItemPackage {
    private List<AbstractItem> items;

    public ItemPackage(List<AbstractItem> items) {
        this.items = items;
    }

    public int getTotalCost() {
        int cost = 0;
        for (AbstractItem item : this.getItems()) {
            cost += item.getCost();
        }

        return cost;
    }

    public int getTotalValue() {
        int cost = 0;
        for (AbstractItem item : this.getItems()) {
            cost += item.getValue();
        }

        return cost;
    }

    public List<AbstractItem> getItems() {
        return this.items;
    }
}
