package org.CustomClass.factory;

import org.CustomClass.CustomArrayList;
import org.CustomClass.Barrel;
import org.CustomClass.Basic;
import org.CustomClass.Enums.BarrelVolume;
import org.CustomClass.Enums.StoredMaterial;

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
        StoredMaterial[] materials = StoredMaterial.values();

        for (int i = 0; i < size; i++) {
            StoredMaterial randomMaterial = materials[random.nextInt(materials.length)];
            double volume = 10 + random.nextDouble() * 90;

            arrayList.add(new Barrel.Builder()
                    .setVolume(volume)
                    .setStoredMaterial(randomMaterial)  // Правильный вызов
                    .setMaterial(randomMaterial.getMaterialName())
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
            scanner.nextLine();

            System.out.print("Введите хранимый материал (Дерево/Металл/Пластик/Стекло/Камень): ");
            String storedMaterialStr = scanner.nextLine();

            System.out.print("Введите материал бочки: ");
            String material = scanner.nextLine();

            try {
                StoredMaterial storedMaterial = StoredMaterial.fromString(storedMaterialStr);
                arrayList.add(new Barrel.Builder()
                        .setVolume(volume)
                        .setStoredMaterial(storedMaterial)
                        .setMaterial(material)
                        .build());
            } catch (IllegalArgumentException e) {
                System.err.println("Ошибка: неверный материал. Используйте Дерево, Металл, Пластик, Стекло или Камень.");
                i--; // Повторяем итерацию
            }
        }
        return arrayList;
    }
}