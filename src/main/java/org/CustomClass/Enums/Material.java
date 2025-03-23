package org.CustomClass.Enums;

// Перечисление для материала бочки
public enum storedMaterial {
    WOOD("Дерево"),
    METAL("Металл"),
    PLASTIC("Пластик"),
    GLASS("Стекло"),
    STONE("Камень");

    private final String materialName;

    // Конструктор для инициализации названия материала
    BarrelMaterial(String materialName) {
        this.materialName = materialName;
    }

    // Геттер для получения названия материала
    public String getMaterialName() {
        return materialName;
    }
}