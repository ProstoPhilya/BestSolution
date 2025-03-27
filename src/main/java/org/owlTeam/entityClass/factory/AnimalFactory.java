package org.owlTeam.entityClass.factory;

import org.owlTeam.CustomArrayList;
import org.owlTeam.entityClass.Animal;
import org.owlTeam.entityClass.Basic;
import org.owlTeam.entityClass.enums.EyeColors;
import org.owlTeam.entityClass.enums.Species;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class AnimalFactory implements FactoryStrategy<Basic> {

    @Override
    public CustomArrayList<Basic> fromFile(String fileName, int size) {
        CustomArrayList<Basic> arrayList = new CustomArrayList<>(size);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            for (int i = 0; i < size; i++) {
                try {
                    Object object = ois.readObject();
                    if (object instanceof Animal animal){
                        animal.validate();
                        arrayList.add(animal);
                    }
                } catch (ClassNotFoundException e) {
                    System.err.println("Неизвестный класс в файле: " + e.getMessage());
                }
            }
        }catch (StreamCorruptedException e) {
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
        Species[] species = Species.values();
        EyeColors[] eyeColors = EyeColors.values();
        String eyeColor;
        String specie;
        boolean fur;
        int age;

        for (int i = 0; i < size; i++) {
            specie = String.valueOf(species[random.nextInt(Species.values().length)].getName());
            eyeColor = String.valueOf(eyeColors[random.nextInt(EyeColors.values().length)].getName());
            fur = random.nextBoolean();
            age = random.nextInt(1,31);
            arrayList.add(new Animal.Builder()
                    .species(specie)
                    .eyeColor(eyeColor)
                    .fur(fur)
                    .age(age)
                    .build());
        }
        return arrayList;
    }

    @Override
    public CustomArrayList<Basic> fromConsole(Scanner scanner, int size) {
        CustomArrayList<Basic> arrayList = new CustomArrayList<>(size);

        for (int i = 0; i < size; i++) {
            System.out.println("Создание Животного");
            System.out.print("Введите вид Животного: ");
            String species = scanner.nextLine();


            System.out.print("Введите цвет глаз Животного: ");
            String eyeColor = scanner.nextLine();

            System.out.print("Есть у животного шерсть? (Да|Нет): ");
            boolean fur;
            String input;
            do {
                input = scanner.nextLine(); // Считывание строки

                if ("Да".equalsIgnoreCase(input)) {
                    fur = true;
                    break;
                } else if ("Нет".equalsIgnoreCase(input)) {
                    fur = false;
                    break;
                } else {
                    System.out.println("Неверный ввод. Пожалуйста, введите 'Да' или 'Нет'.");
                }
            } while (true);

            System.out.print("Введите взраст животного: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            try {
                arrayList.add(new Animal.Builder()
                        .species(species)
                        .eyeColor(eyeColor)
                        .fur(fur)
                        .age(age)
                        .build());
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage() + "Повторите ввод");
                i--;
            }
        }
        return arrayList;
    }
}
