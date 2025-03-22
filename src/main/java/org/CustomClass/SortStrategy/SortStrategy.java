package org.CustomClass.SortStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SortStrategy<T extends Comparable<T>> implements SortingStrategy<T> {
    @Override
    public void sortInt(List<T> items, Function<T, Integer> fieldGetter) {
        for (int i = 1; i < items.size(); i++) {
            T key = items.get(i);
            int j = i - 1;

            while (j >= 0 && items.get(j).compareTo(key) > 0) {
                items.set(j + 1, items.get(j--));
            }
            items.set(j + 1, key);
        }
    }

    @Override
    public void sortStr(List<T> items, Function<T, String> fieldGetter) {
        for (int i = 1; i < items.size(); i++) {
            T key = items.get(i);
            int j = i - 1;

            while (j >= 0 && items.get(j).compareTo(key) > 0) {
                items.set(j + 1, items.get(j--));
            }
            items.set(j + 1, key);
        }
    }

    @Override
    public void sortEven(List<T> items, Function<T, Integer> fieldGetter) {
        List<T> even = new ArrayList<>();
        int index =  0;

        // добавляем все четные элементы в even ArrayList
        for (T item : items) {
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
        for (int i = 0; i < items.size(); i++) {
            if (fieldGetter.apply(items.get(i)) % 2 == 0) {
                items.set(i, even.get(index++));
            }
        }
    }

    @Override
    public void sortOdd(List<T> items, Function<T, Integer> fieldGetter) {
        List<T> odd = new ArrayList<>();
        int index =  0;

        // добавляем все четные элементы в even ArrayList
        for (T item : items) {
            if (fieldGetter.apply(item) % 2 != 0) {
                odd.add(item);
            }
        }

        // сортеруем все четные элементы в even ArrayList
        for (int i = 1; i < odd.size(); i++) {
            T key = odd.get(i);
            int j = i - 1;

            while (j >= 0 && fieldGetter.apply(odd.get(j)) > fieldGetter.apply(key)) {
                odd.set(j + 1, odd.get(j--));
            }
            odd.set(j + 1, key);
        }

        // возвращем все четные элементы в исходный ArrayList
        for (int i = 0; i < items.size(); i++) {
            if (fieldGetter.apply(items.get(i)) % 2 == 0) {
                items.set(i, odd.get(index++));
            }
        }
    }
}
