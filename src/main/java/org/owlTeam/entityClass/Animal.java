package org.owlTeam.entityClass;

import java.util.ArrayList;
import java.util.List;

public class Animal extends Basic {
    private String species;
    private String eyeColor;
    private boolean furry;
    private int age;

    private Animal(Builder builder) {
        this.species = builder.species;
        this.eyeColor = builder.eyeColor;
        this.furry = builder.furry;
        this.age = builder.age;
    }

    public String getSpecies() {
        return species;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public boolean isWool() {
        return furry;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int getIntValue() {
        return age;
    }

    public void validate() {
        List<String> errors = new ArrayList<>();

        if (!Util.isStringValid(species)) {
            errors.add("Поле 'Вид' заполнено неправильно.");
        }
        if (!Util.isStringValid(eyeColor)) {
            errors.add("Поле 'Цвет глаз' заполнен неправильно.");
        }
        if (!Util.isIntValid(age)) {
            errors.add("Поле 'Возраст' заполнен неправильно.");
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", errors));
        }
    }

    @Override
    public int compareTo(Basic o) {
        Animal other = (Animal) o;
        int comparison = Util.comparingString(Animal::getSpecies).compare(this, other);
        if (comparison != 0) return comparison;

        comparison = Util.comparingString(Animal::getEyeColor).compare(this, other);
        if (comparison != 0) return comparison;

        comparison = Util.comparingInt(Animal::getAge).compare(this, other);
        if (comparison != 0) return comparison;

        return Util.comparingBoolean(Animal::isWool).compare(this, other);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "Вид='" + species + '\'' +
                ", Цвет глаз='" + eyeColor + '\'' +
                ", age=" + age +
                ", Волосатый=" + (furry ? "Да" : "Нет") + '\'' +
                '}';
    }

    public static class Builder {
        private String species;
        private String eyeColor;
        private boolean furry;
        private int age;

        public Builder() {}

        public Builder species(String species) {
            this.species = species;
            return this;
        }

        public Builder eyeColor(String eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        public Builder fur(boolean fur) {
            furry = fur;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Animal build() {
            if (!Util.isStringValid(species)) throw new IllegalArgumentException("Поле вид заполненно неправильно.");
            if (!Util.isStringValid(eyeColor)) throw new IllegalArgumentException("Цвет глаз заполнен неправильно.");
            if (!Util.isIntValid(age)) throw new IllegalArgumentException("Возраст заполнен неправильно.");
            return new Animal(this);
        }
    }
}
