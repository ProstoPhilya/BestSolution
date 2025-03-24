package org.owlTeam.sortStrategy;

import org.owlTeam.CustomArrayList;

public final class SortStrategy {
    public static <T extends Comparable<T>> void sort(CustomArrayList<T> items) {
        for (int i = 1; i < items.size(); i++) {
            T key = items.get(i);
            int j = i - 1;

            // Перемещаем элементы массива, которые больше чем key, на одну позицию вперед
            while (j >= 0 && items.get(j).compareTo(key) > 0) { //При определёных типах выглядело бы items[j] > key
                items.set(j + 1, items.get(j--));
            }
            items.set(j + 1, key);
        }
    }
}
