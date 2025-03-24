package org.owlTeam.entityClass.enums;

public enum Gender {
    MALE("Мужской"),
    FEMALE("Женский");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Gender fromValue(String value) {
        for (Gender gender : Gender.values()) {
            if (gender.getValue().equalsIgnoreCase(value.trim())) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Неизвестное значение пола: " + value);
    }
}