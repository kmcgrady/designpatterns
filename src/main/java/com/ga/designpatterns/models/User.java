package com.ga.designpatterns.models;

import com.ga.designpatterns.models.users.PriceInsensitiveUser;
import com.ga.designpatterns.models.users.PriceSensitiveUser;
import com.ga.designpatterns.strategies.PackageStrategy;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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

    @OneToOne
    private SalesFunnel salesFunnel;

    public User() {}
    public User(String name, int budget, SalesFunnel salesFunnel) {
        this.name = name;
        this.budget = budget;
        this.salesFunnel = salesFunnel;
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

    public SalesFunnel getSalesFunnel() {
        return salesFunnel;
    }

    public void setSalesFunnel(SalesFunnel salesFunnel) {
        this.salesFunnel = salesFunnel;
    }

//    TODO Factory Pattern
    public static User createUser(String name, int budget, SalesFunnel salesFunnel) {
        if (budget < SENSITIVE_BUDGET) {
            return new PriceSensitiveUser(name, budget, salesFunnel);
        } else {
            return new PriceInsensitiveUser(name, budget, salesFunnel);
        }
    }

//    TODO Strategy Pattern
    public abstract PackageStrategy getStrategy();

    public ItemPackage offerPackage(List<AbstractItem> allItems) {
        return this.getStrategy().getItemPackage(this.budget, allItems);
    }
}
