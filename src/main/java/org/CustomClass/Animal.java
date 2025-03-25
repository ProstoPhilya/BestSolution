package org.CustomClass;

public class Animal extends Basic {
    private static final Long serialVersionUID = 1L;
    private String species;
    private String eyeColor;
    private boolean isWool;
    private int age;

    private Animal(Builder builder) {
        this.species = builder.species;
        this.eyeColor = builder.eyeColor;
        this.isWool = builder.isWool;
        this.age = builder.age;
    }

    public String getSpecies() {
        return species;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public boolean isWool() {
        return isWool;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int getIntValue() {
        return age;
    }

    public void validate() {
        if (!Util.isStringValid(species)) throw new IllegalArgumentException("Поле вид заполненно неправильно.");
        if (!Util.isStringValid(eyeColor)) throw new IllegalArgumentException("Цвет глаз заполнен неправильно.");
        if (!Util.isIntValid(age)) throw new IllegalArgumentException("Возраст заполнен неправильно.");
    }

    @Override
    public int compareTo(Basic o) {
        Animal other = (Animal) o;
        int comparison = Util.comparingString(Animal::getSpecies).compare(this, other);
        if (comparison != 0) return comparison;

        comparison = Util.comparingString(Animal::getEyeColor).compare(this, other);
        if (comparison != 0) return comparison;

        return Util.comparingBoolean(Animal::isWool).compare(this, other);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "species='" + species + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", isWool=" + isWool + '\'' +
                ", age=" + age +
                '}';
    }

    public static class Builder {
        private String species;
        private String eyeColor;
        private boolean isWool;
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

        public Builder wool(boolean wool) {
            isWool = wool;
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
