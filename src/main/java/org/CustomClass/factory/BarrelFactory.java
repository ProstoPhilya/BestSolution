package org.CustomClass.factory;

import org.CustomClass.CustomArrayList;
import org.CustomClass.Barrel;
import org.CustomClass.Basic;
import org.CustomClass.Enums.BarrelVolume;
import org.CustomClass.Enums.storedMaterial;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class BarrelFactory implements FactoryStrategy<Basic> {

    @Override
    public CustomArrayList<Basic> fromFile(String fileName, int size) throws IOException, ClassNotFoundException {
        CustomArrayList<Basic> arrayList = new CustomArrayList<>(size);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            for (int i = 0; i < size; i++) {
                Object object = ois.readObject();
                if (object instanceof Barrel) {
                    Barrel barrel = (Barrel) object;
                    barrel.validate();
                    arrayList.add(barrel);
                }
            }
        } catch (StreamCorruptedException e) {
            System.err.println("Ошибка: файл поврежден.");
        } catch (EOFException e) {
            System.err.println("Ошибка: достигнут конец файла.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    @Override
    public CustomArrayList<Basic> fromGenerator(int size) {
        Random random = new Random();
        CustomArrayList<Basic> arrayList = new CustomArrayList<>(size);
        BarrelVolume[] volumes = BarrelVolume.values(); // Все доступные объемы
        storedMaterial[] materials = storedMaterial.values();

        for (int i = 0; i < size; i++) {
            String material = String.valueOf(materials[random.nextInt(materials.length)]);
            String storedMaterial = storedMaterial[random.nextInt(storedMaterial.length())];
            double volume = random.nextDouble() * 100; // Объем от 0 до 100

            arrayList.add(new Barrel.Builder()
                    .setVolume(volume)
                    .toString()
                    .strip(material)
                    .build());
        }
        return arrayList;
    }

    @Override
    public CustomArrayList<Basic> fromConsole(Scanner scanner, int size) {
        CustomArrayList<Basic> arrayList = new CustomArrayList<>(size);

        for (int i = 0; i < size; i++) {
            System.out.println("Создание бочки " + (i + 1) + ":");
            System.out.print("Введите объем: ");
            double volume = scanner.nextDouble();
            scanner.nextLine(); // Очистка буфера

            System.out.print("Введите хранимый материал: ");
            String storedMaterial = scanner.nextLine();

            System.out.print("Введите материал бочки: ");
            String material = scanner.nextLine();

            arrayList.add(new Barrel.Builder()
                    .setVolume(volume)
                    .toString(storedMaterial)
                    .setMaterial(material)
                    .build());
        }
        return arrayList;
    }
}