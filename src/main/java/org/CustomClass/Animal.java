package org.CustomClass;

import org.CustomClass.Saved.Savable;
import org.CustomClass.Saved.WriteToFile;

import java.io.*;
import java.util.Objects;

public class Animal implements Savable, Serializable, Comparable<Animal> {
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

    public void validate() {
        if (!Util.isStringValid(species)) throw new IllegalArgumentException("Поле вид заполненно неправильно.");
        if (!Util.isStringValid(eyeColor)) throw new IllegalArgumentException("Цвет глаз заполнен неправильно.");
        if (!Util.isIntValid(age)) throw new IllegalArgumentException("Возраст заполнен неправильно.");
    }

    @Override
    public int compareTo(Animal o) {
        int comparison = Util.comparingString(Animal::getSpecies).compare(this, o);
        if (comparison != 0) return comparison;

        comparison = Util.comparingString(Animal::getEyeColor).compare(this, o);
        if (comparison != 0) return comparison;

        return Util.comparingBoolean(Animal::isWool).compare(this, o);
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

    @Override
    public void saveToFile(String fileName) throws IOException {
        String data = String.format("%s,%s,%b,%d\n", species, eyeColor, isWool, age);
        WriteToFile.writeDataToFile(fileName, data);
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
