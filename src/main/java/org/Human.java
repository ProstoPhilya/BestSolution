package org;

import org.CustomClass.Enums.Gender;

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
            return new Human(this);
        }
    }
}