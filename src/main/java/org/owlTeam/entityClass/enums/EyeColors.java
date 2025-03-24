package org.owlTeam.entityClass.enums;

public enum EyeColors {
    BLACK("Чёрыне"),
    BROWN("Коричневые"),
    BLUE("Голубые"),
    GREEN("Зелёные");

    private final String name;

    EyeColors(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
