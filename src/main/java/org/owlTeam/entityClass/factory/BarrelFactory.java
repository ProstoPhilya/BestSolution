package org.owlTeam.entityClass.factory;

import org.owlTeam.CustomArrayList;
import org.owlTeam.entityClass.Barrel;
import org.owlTeam.entityClass.Basic;
import org.owlTeam.entityClass.enums.Material;
import org.owlTeam.entityClass.enums.StoredMaterial;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class BarrelFactory implements FactoryStrategy<Basic> {

    @Override
    public CustomArrayList<Basic> fromFile(String fileName, int size) {
        CustomArrayList<Basic> arrayList = new CustomArrayList<>(size);
        int i = 0;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            for (; i < size; i++) {
                Object object = ois.readObject();
                if (object instanceof Barrel barrel) {
                    barrel.validate();
                    arrayList.add(barrel);
                }
            }
        }
        catch (IOException | ClassNotFoundException e) {
            if (e instanceof StreamCorruptedException) {
                System.err.println("Ошибка десериализации: данные повреждены.");
            }
            else if(e instanceof FileNotFoundException) {
                System.out.println("Файл не найден: " + e.getMessage());
            }
            else if(e instanceof EOFException){
                System.out.println("Конец файла. Хотите дозаполнить случайными животными?(Да/Нет)");
                Scanner scanner = new Scanner(System.in);
                if (scanner.nextLine().equals("Да")){
                    arrayList.addAll(this.fromGenerator(size-i));
                    return arrayList;
                }
            }
        }

        return arrayList;
    }

    @Override
    public CustomArrayList<Basic> fromGenerator(int size) {
        Random random = new Random();
        CustomArrayList<Basic> arrayList = new CustomArrayList<>(size);
        int volume;
        StoredMaterial[] storedMaterials = StoredMaterial.values();
        Material[] materials = Material.values();
        String storedMaterial;
        String material;

        for (int i = 0; i < size; i++) {
            volume = random.nextInt(1,31);
            storedMaterial = String.valueOf(storedMaterials[random.nextInt(StoredMaterial.values().length)].getName());
            material = String.valueOf(materials[random.nextInt(Material.values().length)].getName());

            arrayList.add(new Barrel.Builder()
                    .volume(volume)
                    .material(material)
                    .storedMaterial(storedMaterial)
                    .build());
        }
        return arrayList;
    }

    @Override
    public CustomArrayList<Basic> fromConsole(Scanner scanner, int size) {
        CustomArrayList<Basic> arrayList = new CustomArrayList<>(size);

        for (int i = 0; i < size; i++) {
            System.out.println("Создание бочки " + (i + 1) + ":");

            System.out.print("Введите материал бочки: ");
            String material = scanner.nextLine();

            System.out.print("Введите хранимый материал: ");
            String storedMaterial = scanner.nextLine();

            System.out.print("Введите объем: ");
            int volume = scanner.nextInt();
            scanner.nextLine();

            arrayList.add(new Barrel.Builder()
                    .material(material)
                    .storedMaterial(storedMaterial)
                    .volume(volume)
                    .build());
        }
        return arrayList;
    }
}