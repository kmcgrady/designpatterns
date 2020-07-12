package com.ga.designpatterns.models.users;

import com.ga.designpatterns.models.SalesFunnel;
import com.ga.designpatterns.models.User;
import com.ga.designpatterns.strategies.MaximizeValueStrategy;
import com.ga.designpatterns.strategies.PackageStrategy;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class PriceInsensitiveUser extends User {
    public PriceInsensitiveUser() { super(); }
    public PriceInsensitiveUser(String name, int budget, SalesFunnel salesFunnel) {
        super(name, budget, salesFunnel);
    }

    public PackageStrategy getStrategy() {
        return new MaximizeValueStrategy();
    }
}
