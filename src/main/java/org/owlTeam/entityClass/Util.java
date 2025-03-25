package org.owlTeam.entityClass;

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

            if (key1 == key2) {
                return 0;
            } else if (key1 && !key2) {
                return 1;
            } else {
                return -1;
            }
        };
    }

    public static <T> Comparator<T> comparingString(Function<T, String> keyExtractor) {
        return (o1, o2) -> {
            String key1 = keyExtractor.apply(o1);
            String key2 = keyExtractor.apply(o2);
            int minLength = Math.min(key1.length(), key2.length());

            for (int i = 0; i < minLength; i++) {
                char c1 = key1.charAt(i);
                char c2 = key2.charAt(i);

                if (c1 != c2)
                    return c1 - c2;
            }

            return key1.length() - key2.length();
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

    public static boolean isDoubleValid(double volume) {
        return volume > 0 && volume <= 100;
    }

    public static Boolean comparingDouble(Object getVolume) {
        return null;
    }
}
