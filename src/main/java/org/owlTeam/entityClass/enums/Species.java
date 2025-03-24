package org.owlTeam.entityClass.enums;

public enum Species {
    DOG("Собака"),
    CAT("Кот"),
    BIRD("Птица"),
    FISH("Рыба");

    private final String name;

    Species(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
