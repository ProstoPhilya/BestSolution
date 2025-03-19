package org.CustomClass;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

public class Animal implements Externalizable, Comparable<Animal> {
    private static final Long serialVersionUID = 1L;
    private String species;
    private String eyeColor;
    private boolean isWool;

    private Animal(Builder builder) {
        this.species = builder.species;
        this.eyeColor = builder.eyeColor;
        this.isWool = builder.isWool;
    }

    public Animal(){}

    public String getSpecies() {
        return species;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public boolean isWool() {
        return isWool;
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
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(species);
        out.writeObject(eyeColor);
        out.writeBoolean(isWool);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        String tSpecies = (String) in.readObject();
        if (Util.isStringValid(tSpecies)) species = tSpecies;
        else throw new ClassCastException("Invalid species");

        String tEyeColor = (String) in.readObject();
        if (Util.isStringValid(tEyeColor)) eyeColor = tEyeColor;
        else throw new ClassCastException("Invalid eyeColor");

        isWool = in.readBoolean();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return isWool == animal.isWool && Objects.equals(species, animal.species) && Objects.equals(eyeColor, animal.eyeColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(species, eyeColor, isWool);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "species='" + species + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", isWool=" + isWool + '\'' +
                '}';
    }

    public static class Builder {
        private String species;
        private String eyeColor;
        private boolean isWool;

        public Builder() {}

        public Builder species(String species) throws Exception {
            if (!Util.isStringValid(species)) throw new Exception("Invalid species");
            this.species = species;
            return this;
        }

        public Builder eyeColor(String eyeColor) throws Exception {
            if (!Util.isStringValid(eyeColor)) throw new Exception("Invalid eyeColor");
            this.eyeColor = eyeColor;
            return this;
        }

        public Builder wool(boolean wool) {
            isWool = wool;
            return this;
        }

        public Animal build() {
            return new Animal(this);
        }
    }
}
