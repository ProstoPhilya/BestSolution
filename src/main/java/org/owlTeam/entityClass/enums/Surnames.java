package org.owlTeam.entityClass.enums;

public enum Surnames {
    IVANOV("Иванов"),
    PETROV("Петров"),
    SIDOROV("Сидоров"),
    LVOV("Львов"),
    EICHOLZ("Эйхольс");

    private final String name;

    Surnames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
