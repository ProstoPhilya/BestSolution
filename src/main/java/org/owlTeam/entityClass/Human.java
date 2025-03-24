package org.owlTeam.entityClass;

import org.owlTeam.entityClass.enums.Gender;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;

public class Human extends BasicClass implements Serializable, Comparable<Human> {
    private Gender gender;
    private int age;
    private String surname;

    private Human(HumanBuilder builder) {
        this.gender = builder.gender;
        this.age = builder.age;
        this.surname = builder.surname;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Human{" +
                "gender=" + gender.getValue() +
                ", age=" + age +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public int compareTo(Human o) {
        return Comparator.comparing(Human::getGender)
                .thenComparingInt(Human::getAge)
                .thenComparing(Human::getSurname)
                .compare(this, o);
    }

    @Override
    public void saveToFile(String fileName) throws IOException {
        String data = String.format("%s,%d,%s\n", gender.getValue(), age, surname);

        Path path = Paths.get(fileName);

        Files.createDirectories(path.getParent());

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(data);
        }
    }

    public static class HumanBuilder {
        private Gender gender;
        private int age;
        private String surname;

        public HumanBuilder setGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public HumanBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public HumanBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Human build() {
            if (!Util.isStringValid(gender.getValue())) {
                throw new IllegalArgumentException("Пол должен быть установлен");
            }
            if (!Util.isIntValid(age)) {
                throw new IllegalArgumentException("Возраст не может быть отрицательным");
            }

            return new Human(this);
        }
    }
}