package org.owlTeam.entityClass.factory;

import org.owlTeam.CustomArrayList;
import org.owlTeam.entityClass.Basic;
import org.owlTeam.entityClass.Human;
import org.owlTeam.entityClass.enums.Gender;
import org.owlTeam.entityClass.enums.Surnames;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class HumanFactory implements FactoryStrategy<Basic> {

    @Override
    public CustomArrayList<Basic> fromFile(String fileName, int size) {
        CustomArrayList<Basic> arrayList = new CustomArrayList<>(size);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            for (int i = 0; i < size; i++) {
                try {
                    Object object = ois.readObject();
                    if (object instanceof Human human) {
                        human.validate();
                        arrayList.add(human);
                    }
                } catch (ClassNotFoundException e) {
                    System.err.println("Неизвестный класс в файле: " + e.getMessage());
                }
            }
        } catch (StreamCorruptedException e) {
            System.err.println("Файл поврежден: " + fileName);
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + fileName);
        } catch (EOFException e) {
            System.out.println("Достигнут конец файла. Дозаполнить случайными людьми? (Да/Нет)");
            String answer = new Scanner(System.in).nextLine();
            if (answer.equalsIgnoreCase("Да")) {
                arrayList.addAll(this.fromGenerator(size - arrayList.size()));
            }
        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        }

        return arrayList;
    }

    @Override
    public CustomArrayList<Basic> fromGenerator(int size) {
        Random random = new Random();
        CustomArrayList<Basic> arrayList = new CustomArrayList<>(size);
        Surnames[] surnames = Surnames.values();
        Gender[] genders = Gender.values();
        Gender gender;
        String surname;
        int age;

        for (int i = 0; i < size; i++) {
            surname = String.valueOf(surnames[random.nextInt(Surnames.values().length)].getName());
            age = random.nextInt(1,94);
            gender = genders[random.nextInt(genders.length)];

            arrayList.add(new Human.HumanBuilder()
                    .setAge(age)
                    .setGender(gender)
                    .setSurname(surname)
                    .build());
        }
        return arrayList;
    }

    @Override
    public CustomArrayList<Basic> fromConsole(Scanner scanner, int size) {
        CustomArrayList<Basic> arrayList = new CustomArrayList<>(size);

        for (int i = 0; i < size; i++) {
            System.out.println("Создание Человека:");

            Gender gender = null;
            while (gender == null) {
                System.out.println("Выберите пол Человека:");
                System.out.println("1 — мужчина");
                System.out.println("2 — женщина");
                System.out.print("Введите номер: ");

                String input = scanner.nextLine().trim();
                if (input.equals("1")) {
                    gender = Gender.MALE;
                } else if (input.equals("2")) {
                    gender = Gender.FEMALE;
                } else {
                    System.out.println("Неверный ввод. Введите 1 или 2.");
                }
            }

            String surname;
            while (true) {
                System.out.print("Введите фамилию Человека: ");
                surname = scanner.nextLine().trim();

                if (surname.isEmpty()) {
                    System.out.println("Фамилия не может быть пустой.");
                } else if (!surname.matches("[А-Яа-яA-Za-z\\-]+")) {
                    System.out.println("Фамилия должна содержать только буквы и дефисы. Без цифр и спецсимволов.");
                } else {
                    break;
                }
            }

            int age = -1;
            while (age < 0) {
                System.out.print("Введите возраст Человека: ");
                String input = scanner.nextLine().trim();
                try {
                    age = Integer.parseInt(input);
                    if (age < 0) {
                        System.out.println("Возраст не может быть отрицательным.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Введите целое число.");
                }
            }

            arrayList.add(new Human.HumanBuilder()
                    .setAge(age)
                    .setGender(gender)
                    .setSurname(surname)
                    .build());
        }

        return arrayList;
    }
}