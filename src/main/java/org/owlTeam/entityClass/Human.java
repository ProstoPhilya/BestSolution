package org.owlTeam.entityClass;

import org.owlTeam.entityClass.enums.Gender;

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
                "gender=" + gender.getValue() +
                ", age=" + age +
                ", surname='" + surname + '\'' +
                '}';
    }

    public void validate() {
        validateField(Util.isStringValid(surname), "Фамилия заполнена неправильно.");
        validateField(Util.isIntValid(age), "Возраст заполнен неправильно.");
        validateField(Util.isEnumValid(gender), "Пол заполнен неправильно.");
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