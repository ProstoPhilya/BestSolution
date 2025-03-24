package org.owlTeam.SortStrategy;

import org.owlTeam.CustomArrayList;

import java.util.function.Function;

public interface SortingStrategy<T> {
    void sort(CustomArrayList<T> items);
    <T extends Comparable<T>> void sort(CustomArrayList<T> items, Function<T, Integer> fieldGetter);

}
