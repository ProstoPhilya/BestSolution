package org.owlTeam.entityClass;

import org.owlTeam.entityClass.enums.Gender;

import java.util.ArrayList;
import java.util.List;

public class Human extends Basic {
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
                "gender=" + gender.getName() +
                ", age=" + age +
                ", surname='" + surname + '\'' +
                '}';
    }

    public void validate() {
        List<String> errors = new ArrayList<>();

        if (!Util.isStringValid(surname)) {
            errors.add("Поле 'Фамилия' заполнена неправильно.");
        }
        if (!Util.isEnumValid(gender)) {
            errors.add("Поле 'Цвет глаз' заполнен неправильно.");
        }
        if (!Util.isIntValid(age)) {
            errors.add("Поле 'Возраст' заполнен неправильно.");
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", errors));
        }
    }

    private void validateField(boolean isValid, String errorMessage) {
        if (!isValid) throw new IllegalArgumentException(errorMessage);
    }

    @Override
    public int getIntValue() {
        return age;
    }

    @Override
    public int compareTo(Basic o) {
        Human other = (Human) o;

        int comparison = Util.comparingString(Human::getSurname).compare(this, other);
        if (comparison != 0) return comparison;

        comparison = Util.comparingEnum(Human::getGender).compare(this, other);
        if (comparison != 0) return comparison;

        return Util.comparingInt(Human::getAge).compare(this, other);
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
            if (!Util.isStringValid(gender.getName())) {
                throw new IllegalArgumentException("Пол должен быть установлен");
            }
            if (!Util.isIntValid(age)) {
                throw new IllegalArgumentException("Возраст не может быть отрицательным");
            }

            return new Human(this);
        }
    }
}