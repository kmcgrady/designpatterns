package com.ga.designpatterns.strategies;

import com.ga.designpatterns.models.Item;
import com.ga.designpatterns.models.ItemPackage;

import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class MaximizeValueStrategy implements PackageStrategy {
    public ItemPackage getItemPackage(int budget) {
        // Start with all the items available
        ItemPackage itemPackage = new ItemPackage(Item.getAll());

        // Remove least expensive item one at a time.
        while (itemPackage.getTotalCost() > budget) {
            List<Item> items = itemPackage.getItems();
            Item leastValuableItem = Collections.min(items, new Comparator<Item>() {
                @Override
                public int compare(Item o1, Item o2) {
                    return o1.getValue() - o2.getValue();
                }
            });

            List<Item> newItemsInPackage = items.stream()
                    .filter(item -> item != leastValuableItem)
                    .collect(Collectors.toList());
            itemPackage = new ItemPackage(newItemsInPackage);
        }

        return itemPackage;
    }
}
