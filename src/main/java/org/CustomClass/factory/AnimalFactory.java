package org.CustomClass.factory;

import org.CustomArrayList;
import org.CustomClass.Animal;
import org.CustomClass.Basic;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class AnimalFactory implements FactoryStrategy<Basic> {
    @Override
    public CustomArrayList<Basic> fromFile(String fileName, int size) throws IOException, ClassNotFoundException {
        CustomArrayList<Basic> arrayList = new CustomArrayList<>(size);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            for (int i = 0; i < size; i++) {
                Object object = ois.readObject();
                if (object instanceof Animal){
                    Animal animal = (Animal) object;
                    animal.validate();
                    arrayList.add(animal);
                }
            }
        } catch (StreamCorruptedException e){
        } catch (EOFException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    @Override
    public CustomArrayList<Basic> fromGenerator(int size) {
        Random random = new Random();
        CustomArrayList<Basic> arrayList = new CustomArrayList<>(size);
        String[] species = {"Собака", "Кот", "Слон", "Птица", "Лев"};
        String[] eyeColors = {"Белый", "Черный", "Рыжий", "Серый", "Коричневый"};

        for (int i = 0; i < size; i++) {
            String specie = species[random.nextInt(species.length)];
            String eyeColor = eyeColors[random.nextInt(eyeColors.length)];
            boolean isWool = random.nextBoolean();
            int age = random.nextInt(1,31);
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
    public CustomArrayList<Basic> fromConsole(Scanner scanner, int size) {
        CustomArrayList<Basic> arrayList = new CustomArrayList<>(size);

        for (int i = 0; i < size; i++) {
            System.out.println("Создание Животного:");
            System.out.print("Введите вид Животного: ");
            String species = scanner.nextLine();


            System.out.print("Введите цвет глаз Животного: ");
            String eyeColor = scanner.nextLine();

            System.out.print("Есть у животного шерсть? (true|false): ");
            boolean isWool = scanner.nextBoolean();
            scanner.nextLine();

            System.out.print("Введите взраст животного: ");
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
