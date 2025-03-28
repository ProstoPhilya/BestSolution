package org.owlTeam.entityClass.enums;

public enum Gender {
    MALE("Мужской"),
    FEMALE("Женский");

    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}