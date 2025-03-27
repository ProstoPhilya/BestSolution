package org.owlTeam.entityClass.enums;

public enum Material {
    STEEL("Сталь"),
    WOOD("Дерево"),
    PLASTIC("Пластик");

    private final String name;

    Material(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
