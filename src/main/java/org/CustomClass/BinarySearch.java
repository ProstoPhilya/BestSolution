package org.CustomClass;

import org.execution.CustomArrayList;

public final class BinarySearch {

    public static <T extends Comparable<T>> T search(CustomArrayList<T> arrayList, T element) {
        int left = 0;
        int right = arrayList.size() - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            int comparison = arrayList.get(middle).compareTo(element);

            if (comparison == 0) return arrayList.get(middle);
            else if (comparison > 0) right = middle - 1;
            else left = middle + 1;
        }

        return null;
    }
}
