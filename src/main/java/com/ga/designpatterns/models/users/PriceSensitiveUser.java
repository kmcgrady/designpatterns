package com.ga.designpatterns.models.users;

import com.ga.designpatterns.models.User;
import com.ga.designpatterns.strategies.MaximizeCostStrategy;
import com.ga.designpatterns.strategies.PackageStrategy;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class PriceSensitiveUser extends User {
    public PriceSensitiveUser() { super(); }
    public PriceSensitiveUser(String name, int budget) {
        super(name, budget);
    }

    public PackageStrategy getStrategy() {
        return new MaximizeCostStrategy();
    }
}