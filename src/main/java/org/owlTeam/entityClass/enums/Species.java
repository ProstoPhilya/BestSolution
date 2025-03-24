package org.owlTeam.entityClass.enums;

public enum Species {
    DOG("Dog"),
    CAT("Cat"),
    BIRD("Bird"),
    FISH("Fish");

    private final String name;

    Species(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
