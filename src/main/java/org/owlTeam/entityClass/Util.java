package org.owlTeam.entityClass;

import org.owlTeam.entityClass.enums.Gender;

import java.util.Comparator;
import java.util.function.Function;

public final class Util {
    public static <T> Comparator<T> comparingInt(Function<T, Integer> keyExtractor) {
        return (o1, o2) -> {
            int key1 = keyExtractor.apply(o1);
            int key2 = keyExtractor.apply(o2);
            return key1 - key2;
        };
    }

    public static <T> Comparator<T> comparingBoolean(Function<T, Boolean> keyExtractor) {
        return (o1, o2) -> {
            boolean key1 = keyExtractor.apply(o1);
            boolean key2 = keyExtractor.apply(o2);

            return (key1 == key2) ? 0 : (key1 ? 1 : -1);
        };
    }

    public static <T> Comparator<T> comparingString(Function<T, String> keyExtractor) {
        return (o1, o2) -> {
            String key1 = keyExtractor.apply(o1);
            String key2 = keyExtractor.apply(o2);

            key1 = key1.toLowerCase();
            key2 = key2.toLowerCase();

            return key1.compareTo(key2);
        };
    }

    public static <T, E extends Enum<E>> Comparator<T> comparingEnum(Function<T, E> keyExtractor) {
        return (o1, o2) -> {
            E key1 = keyExtractor.apply(o1);
            E key2 = keyExtractor.apply(o2);
            if (key1 == null && key2 == null) return 0;
            if (key1 == null) return -1;
            if (key2 == null) return 1;
            return key1.compareTo(key2);
        };
    }

    public static boolean isIntValid(int value) {
        return value > 0 && value <= 150;
    }

    public static boolean isStringValid(String value) {
        if (value.isBlank() || value.isEmpty()) return false;
        if (value.length() < 3 || value.length() > 20) return false;

        for (char c : value.toCharArray()) {
            if (Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public static <E extends Enum<E>> boolean isEnumValid(E value) {
        return value != null;
    }
}
