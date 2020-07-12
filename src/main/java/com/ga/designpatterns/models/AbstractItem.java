package com.ga.designpatterns.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public abstract class AbstractItem {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=1, message = "Name must not be empty")
    private String name;

    @ManyToMany(mappedBy = "items")
    private List<ItemPackage> packages;

    public AbstractItem() {}
    public AbstractItem(String name) {
        this.name = name;
    }

    public static List<Item> getAll() { return new ArrayList<Item>(); }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract int getCost();

    public abstract void setCost(int cost);

    public abstract int getValue();

    public abstract void setValue(int value);
}
