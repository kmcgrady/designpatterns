package com.ga.designpatterns.strategies;

import com.ga.designpatterns.models.Item;
import com.ga.designpatterns.models.ItemPackage;

import java.util.List;

public interface PackageStrategy {
    public ItemPackage getItemPackage(int budget, List<Item> fromItems);
}
