package org.CustomClass.SortStrategy;

import java.util.List;
import java.util.function.Function;

public interface SortingStrategy<T> {
    void sortInt(List<T> items, Function<T, Integer> fieldGetter);
    void sortStr(List<T> items, Function<T, String> fieldGetter);
    void sortEven(List<T> items, Function<T, Integer> fieldGetter);
    void sortOdd(List<T> items, Function<T, Integer> fieldGetter);

}
