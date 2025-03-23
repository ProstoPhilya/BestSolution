package org.CustomClass.Enums;

// Перечисление для объема бочки
public enum BarrelVolume {
    SMALL(10.0),   // Маленькая бочка (10 литров)
    MEDIUM(50.0),  // Средняя бочка (50 литров)
    LARGE(100.0);  // Большая бочка (100 литров)

    private final double volume;

    // Конструктор для инициализации объема
    BarrelVolume(double volume) {
        this.volume = volume;
    }

    // Геттер для получения объема
    public double getVolume() {
        return volume;
    }
}