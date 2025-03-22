package org.CustomClass.factory;

import org.CustomClass.BasicClass;
import org.execution.CustomArrayList;
import org.CustomClass.Animal;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class AnimalFactory implements FactoryStrategy<BasicClass> {
    @Override
    public CustomArrayList<BasicClass> fromFile(String fileName, int size) throws IOException, ClassNotFoundException {
        CustomArrayList<BasicClass> arrayList = new CustomArrayList<>(size);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                Animal animal = new Animal.Builder()
                        .species(parts[0])
                        .eyeColor(parts[1])
                        .wool(Boolean.parseBoolean(parts[2]))
                        .age(Integer.parseInt(parts[3]))
                        .build();
                arrayList.add(animal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    @Override
    public CustomArrayList<BasicClass> fromGenerator(int size) {
        Random random = new Random();
        CustomArrayList<BasicClass> arrayList = new CustomArrayList<>(size);
        String[] species = {"Собака", "Кот", "Слон", "Птица", "Лев"};
        String[] eyeColors = {"Белый", "Черный", "Рыжий", "Серый", "Коричневый"};

        for (int i = 0; i < size; i++) {
            String specie = species[random.nextInt(species.length)];
            String eyeColor = eyeColors[random.nextInt(eyeColors.length)];
            boolean isWool = random.nextBoolean();
            int age = random.nextInt(31);

            arrayList.add(new Animal.Builder()
                    .species(specie)
                    .eyeColor(eyeColor)
                    .wool(isWool)
                    .age(age)
                    .build());
        }
        return arrayList;
    }

    @Override
    public CustomArrayList<BasicClass> fromConsole(Scanner scanner, int size) {
        CustomArrayList<BasicClass> arrayList = new CustomArrayList<>(size);

        for (int i = 0; i < size; i++) {
            System.out.println("Создание Животного:");
            System.out.print("Введите вид Животного: ");
            String species = scanner.nextLine();

            System.out.print("Введите цвет глаз Животного: ");
            String eyeColor = scanner.nextLine();

            System.out.print("Есть у животного шерсть? (true|false): ");
            boolean isWool = scanner.nextBoolean();
            scanner.nextLine();

            System.out.print("Введите возраст животного: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            arrayList.add(new Animal.Builder()
                    .species(species)
                    .eyeColor(eyeColor)
                    .wool(isWool)
                    .age(age)
                    .build());
        }
        return arrayList;
    }
}
