package org.owlTeam.SortStrategy;

import org.owlTeam.CustomArrayList;

import java.util.function.Function;

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

    public static <T extends Comparable<T>> void sortEven(CustomArrayList<T> items, Function<T, Integer> fieldGetter) {
        CustomArrayList<T> even = new CustomArrayList<>();
        int index =  0;

        // добавляем все четные элементы в even ArrayList
        for (T item: items) {
            if (fieldGetter.apply(item) % 2 == 0) {
                even.add(item);
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
        for (T item: items) {
            if (fieldGetter.apply(item) % 2 == 0) {
                items.set(items.indexOf(item), even.get(index++));
            }
        }
    }

    public static <T extends Comparable<T>> void sortOdd(CustomArrayList<T> items, Function<T, Integer> fieldGetter) {
        CustomArrayList<T> even = new CustomArrayList<>();
        int index =  0;

        // добавляем все четные элементы в even ArrayList
        for (int i = 0; i < items.size(); i++) {
            if (fieldGetter.apply(items.get(i)) % 2 != 0) {
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
            if (fieldGetter.apply(items.get(i)) % 2 != 0) {
                items.set(i, even.get(index++));
            }
        }
    }
}
