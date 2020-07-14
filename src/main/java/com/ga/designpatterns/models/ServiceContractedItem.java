package com.ga.designpatterns.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "service_contracted_items")
@Entity
public class ServiceContractedItem extends AbstractItem {
    public static double VALUE_MULTIPLIER = 0.5;
    public static double COST_MULTIPLIER = 0.3;

    @OneToOne
    private Item item;

    public ServiceContractedItem() {}

    public ServiceContractedItem(Item item) {
        super("Service Contract for " + item.getName());
        this.item = item;
    }

    // TODO Decorator Pattern
    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public void setValue(int value) {

    }

    @Override
    public int getCost() {
        return 100;
    }

    @Override
    public void setCost(int cost) {

    }
}