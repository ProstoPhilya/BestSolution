package org.owlTeam.SortStrategy;

import org.owlTeam.CustomArrayList;

import java.util.function.Function;
import java.util.function.Predicate;

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

    public static <T extends Comparable<T>> void sortByCondition(CustomArrayList<T> items,
                                                                 Predicate<Integer> condition,
                                                                 Function<T, Integer> fieldGetter) {
        CustomArrayList<T> filteredItems = new CustomArrayList<>();

        for (T item : items) {
            if (condition.test(fieldGetter.apply(item))) {
                filteredItems.add(item);
            }
        }
        for (int i = 1; i < filteredItems.size(); i++) {
            T key = filteredItems.get(i);
            int j = i - 1;

            while (j >= 0 && fieldGetter.apply(filteredItems.get(j)).compareTo(fieldGetter.apply(key)) > 0) {
                filteredItems.set(j + 1, filteredItems.get(j--));
            }
            filteredItems.set(j + 1, key);
        }
        int index = 0;
        for (int i = 0; i < items.size(); i++) {
            if (condition.test(fieldGetter.apply(items.get(i)))) {
                items.set(i, filteredItems.get(index++));
            }
        }
    }
}
