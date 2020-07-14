package com.ga.designpatterns.models.users;

import com.ga.designpatterns.models.SalesFunnel;
import com.ga.designpatterns.models.User;
import com.ga.designpatterns.strategies.MaximizeCostStrategy;
import com.ga.designpatterns.strategies.PackageStrategy;

import javax.persistence.Entity;

@Entity
public class PriceInsensitiveUser extends User {
    public PriceInsensitiveUser() { super(); }
    public PriceInsensitiveUser(String name, int budget, SalesFunnel salesFunnel) {
        super(name, budget, salesFunnel);
    }

    // TODO
    // Override getStrategy() to use the MaximizeCostStrategy.
}
