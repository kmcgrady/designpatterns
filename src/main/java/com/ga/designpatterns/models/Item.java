package com.ga.designpatterns.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Table(name = "items")
@Entity
public class Item extends AbstractItem {
    @NotNull
    @Min(value=0, message = "Cost must be positive")
    private int cost;

    @NotNull
    @Min(value=0, message = "Value must be positive")
    private int value;

    public Item() {}
    public Item(String name, int cost, int value) {
        super(name);
        this.cost = cost;
        this.value = value;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }
}
