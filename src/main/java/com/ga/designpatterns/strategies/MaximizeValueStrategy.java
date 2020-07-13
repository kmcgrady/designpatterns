package com.ga.designpatterns.strategies;

import com.ga.designpatterns.models.AbstractItem;
import com.ga.designpatterns.models.ItemPackage;

import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;

public class MaximizeValueStrategy implements PackageStrategy {
    public ItemPackage getItemPackage(int budget, List<AbstractItem> fromItems) {
        // Start with all the items available
        ItemPackage itemPackage = new ItemPackage(fromItems);

        // Remove least expensive item one at a time.
        while (itemPackage.getTotalCost() > budget) {
            List<AbstractItem> items = itemPackage.getItems();
            AbstractItem leastValuableItem = Collections.min(items, new Comparator<AbstractItem>() {
                @Override
                public int compare(AbstractItem o1, AbstractItem o2) {
                    return o1.getValue() - o2.getValue();
                }
            });

            List<AbstractItem> newItemsInPackage = items.stream()
                    .filter(item -> item != leastValuableItem)
                    .collect(Collectors.toList());
            itemPackage = new ItemPackage(newItemsInPackage);
        }

        return itemPackage;
    }
}
