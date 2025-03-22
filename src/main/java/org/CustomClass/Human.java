package org.CustomClass;

import org.CustomClass.Enums.Gender;

import java.util.Scanner;

public class Human {
    private Gender gender;
    private int age;
    private String surname;

    private Human(HumanBuilder builder) {
        this.gender = builder.gender;
        this.age = builder.age;
        this.surname = builder.surname;
    }

    public static Human fromString(String data) {
        String[] parts = data.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат данных");
        }

        try {
            Gender gender = Gender.fromValue(parts[0].trim());
            int age = Integer.parseInt(parts[1].trim());
            String surname = parts[2].trim();

            return new HumanBuilder()
                    .setGender(gender)
                    .setAge(age)
                    .setSurname(surname)
                    .build();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неверный формат данных", e);
        }
    }

    public static Human fromUserInput(Scanner scanner) {
        System.out.println("Введите пол (Мужской, Женский):");
        Gender gender = Gender.fromValue(scanner.nextLine().trim());

        System.out.println("Введите возраст:");
        int age = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Введите фамилию:");
        String surname = scanner.nextLine().trim();

        return new HumanBuilder()
                .setGender(gender)
                .setAge(age)
                .setSurname(surname)
                .build();
    }

    @Override
    public String toString() {
        return "Human{" +
                "gender=" + gender.getValue() +
                ", age=" + age +
                ", surname='" + surname + '\'' +
                '}';
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
            if (!Util.isStringValid(surname)) {
                throw new IllegalArgumentException("Фамилия должна быть установлена");
            }
            return new Human(this);
        }
    }
}