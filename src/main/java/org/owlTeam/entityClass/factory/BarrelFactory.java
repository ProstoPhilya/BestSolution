package org.owlTeam.entityClass.factory;

import org.owlTeam.CustomArrayList;
import org.owlTeam.entityClass.Barrel;
import org.owlTeam.entityClass.Basic;
import org.owlTeam.entityClass.enums.StoredMaterial;

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
                    .setStoredMaterial(randomMaterial.getMaterialName())  // Используем строковое представление
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
            String storedMaterial = scanner.nextLine();

            System.out.print("Введите материал бочки: ");
            String material = scanner.nextLine();

            arrayList.add(new Barrel.Builder()
                    .setVolume(volume)
                    .setStoredMaterial(storedMaterial)
                    .setMaterial(material)
                    .build());
        }
        return arrayList;
    }
}