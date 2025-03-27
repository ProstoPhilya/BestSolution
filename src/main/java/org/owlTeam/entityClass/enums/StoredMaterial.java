package org.owlTeam.entityClass.enums;

public enum StoredMaterial {
    OIL("Нефть"),
    WATER("Вода"),
    GRAIN("Зерно"),
    BEER("Пиво");

    private final String name;

    StoredMaterial(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
