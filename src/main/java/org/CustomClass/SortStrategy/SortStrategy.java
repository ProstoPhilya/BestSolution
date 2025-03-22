package org.CustomClass.SortStrategy;

import org.CustomArrayList;

public class SortStrategy<T extends Comparable<T>> implements SortingStrategy<T> {
    @Override
    public void sort(CustomArrayList<T> items) {
        for (int i = 1; i < items.size(); i++) {
            T key = items.get(i);
            int j = i - 1;

            while (j >= 0 && items.get(j).compareTo(key) > 0) {
                items.set(j + 1, items.get(j--));
            }
            items.set(j + 1, key);
        }
    }
}
