package org.owlTeam.SortStrategy;

import org.owlTeam.CustomArrayList;

import java.util.function.Function;

public final class SortWithEven {
    public static <T extends Comparable<T>> void sort(CustomArrayList<T> items, Function<T, Integer> fieldGetter) {
        CustomArrayList<T> even = new CustomArrayList<>();
        int index =  0;

        // добавляем все четные элементы в even ArrayList
        for (int i = 0; i < items.size(); i++) {
            if (fieldGetter.apply(items.get(i)) % 2 == 0) {
                even.add(items.get(i));
            }
        }

        // сортеруем все четные элементы в even ArrayList
        for (int i = 1; i < even.size(); i++) {
            T key = even.get(i);
            int j = i - 1;

            while (j >= 0 && fieldGetter.apply(even.get(j)) > fieldGetter.apply(key)) {
                even.set(j + 1, even.get(j--));
            }
            even.set(j + 1, key);
        }

        // возвращем все четные элементы в исходный ArrayList
        for (int i = 0; i < items.size(); i++) {
            if (fieldGetter.apply(items.get(i)) % 2 == 0) {
                items.set(i, even.get(index++));
            }
        }
    }
}
