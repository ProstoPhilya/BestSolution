package org.owlTeam.entityClass.factory;

import org.owlTeam.CustomArrayList;
import org.owlTeam.entityClass.Basic;
import org.owlTeam.entityClass.Human;
import org.owlTeam.entityClass.enums.Gender;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class HumanFactory implements FactoryStrategy<Basic> {
    @Override
    public CustomArrayList<Basic> fromFile(String fileName, int size) throws IOException, ClassNotFoundException {
        CustomArrayList<Basic> arrayList = new CustomArrayList<>(size);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                Human human = new Human.HumanBuilder()
                        .setSurname(parts[0])
                        .setAge(Integer.parseInt(parts[1]))
                        .setGender(Gender.valueOf(parts[2]))
                        .build();
                arrayList.add(human);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    @Override
    public CustomArrayList<Basic> fromGenerator(int size) {
        Random random = new Random();
        CustomArrayList<Basic> arrayList = new CustomArrayList<>(size);
        String[] surnames = {"Иванов", "Петров", "Сидоров", "Эйхольс", "Львов"};
        Gender[] genders = Gender.values();

        for (int i = 0; i < size; i++) {
            String surname = surnames[random.nextInt(surnames.length)];
            int age = random.nextInt(94);
            Gender gender = genders[random.nextInt(genders.length)];

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

            String surname = "";
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

            arrayList.add(new Human.HumanBuilder()
                    .setAge(age)
                    .setGender(gender)
                    .setSurname(surname)
                    .build());
        }

        return arrayList;
    }
}