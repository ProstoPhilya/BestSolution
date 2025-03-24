package org.owlTeam.entityClass.enums;

public enum EyeColor {
    BLACK("Black"),
    BROWN("Brown"),
    BLUE("Blue"),
    GREEN("Green");

    private final String name;

    EyeColor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
