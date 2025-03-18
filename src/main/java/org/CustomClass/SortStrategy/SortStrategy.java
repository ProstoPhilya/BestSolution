package org.CustomClass.SortStrategy;

public class SortStrategy<T extends Comparable<T>> implements SortingStrategy<T> {
    @Override
    public void sort(T[] items) {
        for (int i = 1; i < items.length; i++) {
            T key = items[i];
            int j = i - 1;

            // Перемещаем элементы массива, которые больше чем key, на одну позицию вперед
            while (j >= 0 && items[j].compareTo(key) > 0) { //При определёных типах выглядело бы items[j] > key
                items[j + 1] = items[j];
                j = j - 1;
            }
            items[j + 1] = key;
        }
    }
}
