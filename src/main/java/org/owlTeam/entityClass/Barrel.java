package org.owlTeam.entityClass;

import org.owlTeam.entityClass.enums.StoredMaterial;

public class Barrel extends Basic {
    private static final Long serialVersionUID = 1L;
    private double volume;          // Объем бочки
    private StoredMaterial storedMaterial;  // Теперь это enum
    private String material;        // Материал бочки

    private Barrel(Builder builder) {
        this.volume = builder.volume;
        this.storedMaterial = builder.storedMaterial;  // Храним enum
        this.material = builder.material;
    }

    public double getVolume() {
        return volume;
    }

    // Возвращаем имя материала из enum
    public String getStoredMaterial() {
        return storedMaterial != null ? storedMaterial.getMaterialName() : null;
    }

    public String getMaterial() {
        return material;
    }

    // Остальные методы остаются без изменений
    @Override
    public int getIntValue() {
        return (int) volume;
    }

    public void validate() {
        if (!Util.isDoubleValid(volume)) throw new IllegalArgumentException("Объем заполнен неправильно.");
        if (storedMaterial == null) throw new IllegalArgumentException("Хранимый материал не указан.");
        if (!Util.isStringValid(material)) throw new IllegalArgumentException("Материал бочки заполнен неправильно.");
    }

    @Override
    public int compareTo(Basic o) {
        if (o == null) throw new NullPointerException("Cannot compare with null");
        if (!(o instanceof Barrel)) throw new ClassCastException("Cannot compare Barrel with " + o.getClass().getSimpleName());

        Barrel other = (Barrel) o;
        int volumeComparison = Double.compare(this.getVolume(), other.getVolume());
        if (volumeComparison != 0) return volumeComparison;

        int materialComparison = this.storedMaterial.compareTo(other.storedMaterial);
        if (materialComparison != 0) return materialComparison;

        return this.getMaterial().compareTo(other.getMaterial());
    }

    @Override
    public String toString() {
        return "Barrel{" +
                "volume=" + volume +
                ", storedMaterial=" + (storedMaterial != null ? storedMaterial.getMaterialName() : "null") +
                ", material='" + material + '\'' +
                '}';
    }

    public static class Builder {
        private double volume;
        private StoredMaterial storedMaterial;
        private String material;

        public Builder() {}

        public Builder setVolume(double volume) {
            this.volume = volume;
            return this;
        }

        public Builder setStoredMaterial(StoredMaterial storedMaterial) {
            this.storedMaterial = storedMaterial;
            return this;
        }

        public Builder setMaterial(String material) {
            this.material = material;
            return this;
        }

        public Barrel build() {
            if (!Util.isDoubleValid(volume)) throw new IllegalArgumentException("Объем заполнен неправильно.");
            if (storedMaterial == null) throw new IllegalArgumentException("Хранимый материал не указан.");
            if (!Util.isStringValid(material)) throw new IllegalArgumentException("Материал бочки заполнен неправильно.");
            return new Barrel(this);
        }
    }
}