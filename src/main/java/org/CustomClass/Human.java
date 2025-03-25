package org.CustomClass;

import org.CustomClass.Enums.Gender;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.Objects;

public class Human extends Basic implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Gender gender;
    private final int age;
    private final String surname;

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

    public static Comparator<Human> getComparator() {
        return Comparator.nullsFirst(
                Comparator.comparing(Human::getGender, Comparator.nullsFirst(Comparator.naturalOrder()))
                        .thenComparingInt(Human::getAge)
                        .thenComparing(Human::getSurname, Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER))
        );
    }

    @Override
    public String toString() {
        return "Human{" +
                "gender=" + gender +
                ", age=" + age +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human)) return false;
        Human human = (Human) o;
        return age == human.age &&
                gender == human.gender &&
                Objects.equals(surname, human.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, age, surname);
    }

    public void saveToFile(String fileName) throws IOException {
        Objects.requireNonNull(fileName, "Имя файла не может быть null");
        String data = String.format("%s,%d,%s%n", gender, age, surname);

        Path path = Paths.get(fileName);
        Files.createDirectories(path.getParent());

        try (BufferedWriter writer = Files.newBufferedWriter(path,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {
            writer.write(data);
        }
    }

    @Override
    public int getIntValue() {
        return age;
    }

    @Override
    public void validate() {
        if (gender == null) {
            throw new IllegalArgumentException("Пол должен быть указан");
        }
        if (age < 0 || age > 111) {
            throw new IllegalArgumentException("Возраст должен быть от 0 до 111");
        }
        if (surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Фамилия не может быть пустой");
        }
        if (!surname.matches("[\\p{L}\\s-]+")) {
            throw new IllegalArgumentException("Фамилия содержит недопустимые символы");
        }
    }

    @Override
    public int compareTo(Basic o) {
        if (o == null) return 1;
        if (!(o instanceof Human)) {
            return Integer.compare(this.getIntValue(), o.getIntValue());
        }
        return getComparator().compare(this, (Human) o);
    }

    public static class HumanBuilder {
        private Gender gender;
        private int age;
        private String surname;

        public HumanBuilder setGender(Gender gender) {
            this.gender = Objects.requireNonNull(gender, "Пол не может быть null");
            return this;
        }

        public HumanBuilder setAge(int age) {
            if (age < 0 || age > 111) {
                throw new IllegalArgumentException("Возраст должен быть от 0 до 111");
            }
            this.age = age;
            return this;
        }

        public HumanBuilder setSurname(String surname) {
            this.surname = Objects.requireNonNull(surname, "Фамилия не может быть null");
            if (surname.trim().isEmpty()) {
                throw new IllegalArgumentException("Фамилия не может быть пустой");
            }
            return this;
        }

        public Human build() {
            Human human = new Human(this);
            human.validate();
            return human;
        }
    }
}