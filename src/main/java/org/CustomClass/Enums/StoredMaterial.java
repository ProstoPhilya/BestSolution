package org.CustomClass.Enums;

/**
 * Перечисление материалов, из которых может быть сделана бочка
 */
public enum StoredMaterial {
    WOOD("Дерево"),
    METAL("Металл"),
    PLASTIC("Пластик"),
    GLASS("Стекло"),
    STONE("Камень");

    private final String materialName;

    /**
     * Конструктор enum
     * @param materialName - читаемое название материала
     */
    StoredMaterial(String materialName) {
        this.materialName = materialName;
    }

    /**
     * @return читаемое название материала
     */
    public String getMaterialName() {
        return materialName;
    }

    /**
     * Получение enum по строковому названию (регистронезависимое)
     * @param name - название материала
     * @return соответствующий StoredMaterial
     * @throws IllegalArgumentException если материал не найден
     */
    public static StoredMaterial fromString(String name) {
        for (StoredMaterial material : values()) {
            if (material.materialName.equalsIgnoreCase(name) ||
                    material.name().equalsIgnoreCase(name)) {
                return material;
            }
        }
        throw new IllegalArgumentException("Неизвестный материал: " + name);
    }
}