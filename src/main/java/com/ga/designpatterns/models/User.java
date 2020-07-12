package com.ga.designpatterns.models;

import com.ga.designpatterns.models.users.PriceInsensitiveUser;
import com.ga.designpatterns.models.users.PriceSensitiveUser;
import com.ga.designpatterns.strategies.PackageStrategy;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name = "users")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class User {
    public final static int SENSITIVE_BUDGET = 5000;

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

    public static User createUser(String name, int budget) {
        if (budget < SENSITIVE_BUDGET) {
            return new PriceSensitiveUser(name, budget);
        } else {
            return new PriceInsensitiveUser(name, budget);
        }
    }

    public ItemPackage offerPackage() {
        return this.getStrategy().getItemPackage(this.budget);
    }

    public abstract PackageStrategy getStrategy();
}
