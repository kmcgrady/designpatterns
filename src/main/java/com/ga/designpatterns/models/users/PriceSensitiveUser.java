package com.ga.designpatterns.models.users;

import com.ga.designpatterns.models.SalesFunnel;
import com.ga.designpatterns.models.User;
import com.ga.designpatterns.strategies.MaximizeValueStrategy;
import com.ga.designpatterns.strategies.PackageStrategy;

import javax.persistence.Entity;

@Entity
public class PriceSensitiveUser extends User {
    public PriceSensitiveUser() { super(); }
    public PriceSensitiveUser(String name, int budget, SalesFunnel salesFunnel) {
        super(name, budget, salesFunnel);
    }

    // TODO
    // Override getStrategy() to use the MaximizeValueStrategy.
}
