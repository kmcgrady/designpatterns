package com.ga.designpatterns.models;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=1, message = "Name must not be empty")
    private String name;

    @NotNull
    @Min(value=0, message = "Budget must be positive")
    private int budget;

    public User() {}
    public User(String name, int budget) {
        this.name = name;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
