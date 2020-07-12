package com.ga.designpatterns.strategies;

import com.ga.designpatterns.models.ItemPackage;

public interface PackageStrategy {
    public ItemPackage getItemPackage(int budget);
}
