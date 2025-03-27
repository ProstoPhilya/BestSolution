package org.owlTeam.entityClass;

import java.util.ArrayList;
import java.util.List;

public class Barrel extends Basic {
    private String material;
    private String storedMaterial;
    private int volume;

    private Barrel(Builder builder) {
        this.material = builder.material;
        this.storedMaterial = builder.storedMaterial;
        this.volume = builder.volume;
    }

    public String getMaterial() {
        return material;
    }

    public String getStoredMaterial() {
        return storedMaterial;
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public int getIntValue() {
        return volume;
    }

    public void validate() {
        List<String> errors = new ArrayList<>();

        if (!Util.isStringValid(material)) {
            errors.add("Поле 'Материал бочки' заполнен неправильно.");
        }
        if (!Util.isStringValid(storedMaterial)) {
            errors.add("Поле 'Хранимый материа' заполнен неправильно.");
        }
        if (!Util.isIntValid(volume)) {
            errors.add("Поле 'Объем' заполнено неправильно.");
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", errors));
        }
    }

    @Override
    public int compareTo(Basic o) {
        Barrel other = (Barrel) o;
        int comparison = Util.comparingString(Barrel::getMaterial).compare(this, other);
        if (comparison != 0) return comparison;

        comparison = Util.comparingString(Barrel::getStoredMaterial).compare(this, other);
        if (comparison != 0) return comparison;


        return  Util.comparingInt(Barrel::getVolume).compare(this, other);
    }

    @Override
    public String toString() {
        return "Barrel{" +
                "Бочка ='" + material + '\'' +
                ", Заполнена ='" + storedMaterial + '\'' +
                ", Объём =" + volume +
                '}';
    }

    public static class Builder {
        private String material;
        private String storedMaterial;
        private int volume;

        public Builder() {}

        public Builder material(String material) {
            this.material = material;
            return this;
        }

        public Builder storedMaterial(String storedMaterial) {
            this.storedMaterial = storedMaterial;
            return this;
        }

        public Builder volume(int volume) {
            this.volume = volume;
            return this;
        }

        public Barrel build() {
            if (!Util.isIntValid(volume)) throw new IllegalArgumentException("Объем заполнен неправильно.");
            if (!Util.isStringValid(storedMaterial)) throw new IllegalArgumentException("Хранимый материал не указан.");
            if (!Util.isStringValid(material)) throw new IllegalArgumentException("Материал бочки заполнен неправильно.");
            return new Barrel(this);
        }
    }
}