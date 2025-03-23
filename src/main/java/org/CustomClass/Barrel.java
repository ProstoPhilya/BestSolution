package org.CustomClass;

public class Barrel extends Basic {
    private static final Long serialVersionUID = 1L;
    private double volume;          // Объем бочки
    private String storedMaterial;  // Хранимый материал
    private String material;        // Материал бочки

    private Barrel(Builder builder) {
        this.volume = builder.volume;
        this.storedMaterial = builder.storedMaterial;
        this.material = builder.material;
    }

    public double getVolume() {
        return volume;
    }

    public String getStoredMaterial() {
        return storedMaterial;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public int getIntValue() {
        return (int) volume; // Приводим объем к целому числу для сравнения
    }

    // Валидация данных
    public void validate() {
        if (!Util.isDoubleValid(volume)) throw new IllegalArgumentException("Объем заполнен неправильно.");
        if (!Util.isStringValid(storedMaterial)) throw new IllegalArgumentException("Хранимый материал заполнен неправильно.");
        if (!Util.isStringValid(material)) throw new IllegalArgumentException("Материал бочки заполнен неправильно.");
    }

    // Сравнение объектов Barrel
    @Override
    public int compareTo(Basic o) {
        Barrel other = (Barrel) o;
        int comparison = Util.comparingDouble(Barrel::getVolume).compare(this, other);
        if (comparison != 0) return comparison;

        comparison = Util.comparingString(Barrel::getStoredMaterial).compare(this, other);
        if (comparison != 0) return comparison;

        return Util.comparingString(Barrel::getMaterial).compare(this, other);
    }

    @Override
    public String toString() {
        return "Barrel{" +
                "volume=" + volume +
                ", storedMaterial='" + storedMaterial + '\'' +
                ", material='" + material + '\'' +
                '}';
    }

    // Builder для Barrel
    public static class Builder {
        private double volume;
        private String storedMaterial;
        private String material;

        public Builder() {}

        public Builder volume(double volume) {
            this.volume = volume;
            return this;
        }

        public Builder storedMaterial(String storedMaterial) {
            this.storedMaterial = storedMaterial;
            return this;
        }

        public Builder material(String material) {
            this.material = material;
            return this;
        }

        public Barrel build() {
            if (!Util.isDoubleValid(volume)) throw new IllegalArgumentException("Объем заполнен неправильно.");
            if (!Util.isStringValid(storedMaterial)) throw new IllegalArgumentException("Хранимый материал заполнен неправильно.");
            if (!Util.isStringValid(material)) throw new IllegalArgumentException("Материал бочки заполнен неправильно.");
            return new Barrel(this);
        }
    }
}