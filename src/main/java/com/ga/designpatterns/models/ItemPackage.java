package com.ga.designpatterns.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class ItemPackage {
    @Id
    @GeneratedValue
    private int id;

    @ManyToMany
    private List<Item> items;

    public ItemPackage() {}
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
