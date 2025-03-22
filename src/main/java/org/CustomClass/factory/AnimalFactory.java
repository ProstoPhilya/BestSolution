package org.CustomClass.factory;

import org.CustomClass.Animal;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AnimalFactory implements FactoryStrategy<Animal> {
    @Override
    public List<Animal> fromFile(String fileName, int size) throws IOException, ClassNotFoundException {
        List<Animal> arrayList = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            for (int i = 0; i < size; i++) {
                Animal animal = (Animal) ois.readObject();
                animal.validate();
                arrayList.add(animal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    @Override
    public List<Animal> fromGenerator(int size) {
        Random random = new Random();
        List<Animal> arrayList = new ArrayList<>();
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
    public List<Animal> fromConsole(Scanner scanner, int size) {
        List<Animal> arrayList = new ArrayList<>();

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
