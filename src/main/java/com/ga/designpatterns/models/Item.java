package com.ga.designpatterns.models;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Table(name = "items")
@Entity
public class Item {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=1, message = "Name must not be empty")
    private String name;

    @NotNull
    @Min(value=0, message = "Cost must be positive")
    private int cost;

    @NotNull
    @Min(value=0, message = "Value must be positive")
    private int value;

    @NotNull
    private boolean hasServiceContract;

    public Item() {}
    public Item(String name, int cost, int value, boolean hasServiceContract) {
        this.name = name;
        this.cost = cost;
        this.value = value;
        this.hasServiceContract = hasServiceContract;
    }

    public static Collection<Item> getAll() { return new ArrayList<Item>(); }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isHasServiceContract() {
        return hasServiceContract;
    }

    public void setHasServiceContract(boolean hasServiceContract) {
        this.hasServiceContract = hasServiceContract;
    }
}
