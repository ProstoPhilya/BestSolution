package org;

import org.CustomClass.Enums.Gender;

import java.util.Scanner;

class Human {

    private Gender gender;
    private int age;
    private String surname;

    private Human(HumanBuilder builder) {
        this.gender = builder.gender;
        this.age = builder.age;
        this.surname = builder.surname;
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
            if (gender == null) {
                throw new IllegalArgumentException("Пол должен быть установлен");
            }
            if (age < 0) {
                throw new IllegalArgumentException("Возраст не может быть отрицательным");
            }
            if (surname == null || surname.trim().isEmpty()) {
                throw new IllegalArgumentException("Фамилия должна быть установлена");
            }
            return new Human(this);
        }
    }

    public static Human fromString(String data) {
        String[] parts = data.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат данных");
        }

        try {
            Gender gender = Gender.valueOf(parts[0].trim());
            int age = Integer.parseInt(parts[1].trim());
            String surname = parts[2].trim();

            return new HumanBuilder()
                    .setGender(gender)
                    .setAge(age)
                    .setSurname(surname)
                    .build();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Верный формат данных", e);
        }
    }

    public static Human fromUserInput(Scanner scanner) {
        System.out.println("Введите пол (MALE, FEMALE):");
        Gender gender = Gender.valueOf(scanner.nextLine().trim());

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
                "gender=" + gender +
                ", age=" + age +
                ", surname='" + surname + '\'' +
                '}';
    }
}