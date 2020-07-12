package com.ga.designpatterns.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "service_contracted_items")
@Entity
public class ServiceContractedItem extends AbstractItem {
    public static double VALUE_MULTIPLIER = 1.3;
    public static double COST_MULTIPLIER = 1.2;

    @OneToOne
    private Item item;

    public ServiceContractedItem() {}

    public ServiceContractedItem(Item item) {
        super(item.getName() + " (with Service Contract)");
        this.item = item;
    }

    @Override
    public int getValue() {
        int itemValue = this.item.getValue();
        return (int) (VALUE_MULTIPLIER * itemValue);
    }

    @Override
    public void setValue(int value) {
        this.item.setValue(value);
    }

    @Override
    public int getCost() {
        int itemCost = this.item.getCost();
        return (int) (COST_MULTIPLIER * itemCost);
    }

    @Override
    public void setCost(int cost) {
        this.item.setCost(cost);
    }
}