package org.execution;

import org.CustomClass.Animal;
import org.CustomClass.factory.AnimalFactory;

import java.util.Scanner;

public class Main {
    private static void sout_menu(){
        System.out.println("1 - Указать размер массива");
        System.out.println("10 - Заполнить массив Animal с файла");
        System.out.println("\t11 - Заполнить массив Animal случайно");
        System.out.println("\t12 - Заполнить массив Animal случайно вручную");
        System.out.println("20 - Заполнить массив Barrel с файла");
        System.out.println("\t21 - Заполнить массив Barrel случайно");
        System.out.println("\t22 - Заполнить массив Barrel случайно вручную");
        System.out.println("30 - Заполнить массив Human с файла");
        System.out.println("\t31 - Заполнить массив Human случайно");
        System.out.println("\t32 - Заполнить массив Human случайно вручную\n");

        System.out.println("4 - Отсортировать массив");
        System.out.println("\t41 - Отсортировать нечётные классы массива");
        System.out.println("\t42 - Отсортировать чётные классы массива");
        System.out.println("5 - Поиск нужного класса в массиве");
        System.out.println("6 - Записать отсортированный массив в файл");


        System.out.println("7 - показать список комманд");
        System.out.println("8 - Выход");
        System.out.print("Готов к вводу");
    }

    public static void main(String[] args) {
        int userCommand;
        int sizeArray = 10;
        boolean running = true;
        String fileName;
        Scanner scanner = new Scanner(System.in);
        AnimalFactory animalFactory = new AnimalFactory();

        CustomArrayList<BasicClass> arrayList = new CustomArrayList<>(sizeArray);

        System.out.println("Введите команду:");
        //sout_menu();

        try {
            while (running) {
                System.out.print("->");
                userCommand = scanner.nextInt();
                switch (userCommand) {
                    case 1:
                        System.out.print("Размер массива = ");
                        sizeArray = scanner.nextInt();
                        break;
                    case 10:
                        scanner.nextLine();
                        System.out.print("Укажите файл: ");
                        fileName = scanner.next();
                        arrayList.addAll(animalFactory.fromFile(fileName, sizeArray));
                        arrayList.print();
                        break;
                    case 11:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                        }
                        System.out.println("Рандом Animal " + sizeArray);
                        //arrayList = animalFactory.fromGenerator(sizeArray);
                        arrayList.print();
                        break;
                    case 12:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                            scanner.nextLine();
                        }
                        System.out.println("Ручной Animal " + sizeArray);
                        //arrayList = animalFactory.fromConsole(scanner, sizeArray);
                        arrayList.print();
                        break;
                    case 20:
                        scanner.nextLine();
                        System.out.print("Укажите файл: ");
                        fileName = scanner.next();
                        System.out.println("Из файла " + fileName + " заполнить массив Barrel");
                        break;
                    case 21:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                        }
                        System.out.println("Рандом Barrel " + sizeArray);
                        break;
                    case 22:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                            scanner.nextLine();
                        }
                        System.out.println("Ручной Barrel " + sizeArray);
                        break;
                    case 30:
                        scanner.nextLine();
                        System.out.print("Укажите файл: ");
                        fileName = scanner.next();
                        System.out.println("Из файла " + fileName + " заполнить массив Human");
                        break;
                    case 31:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                        }
                        System.out.println("Рандом Human " + sizeArray);
                        break;
                    case 32:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                            scanner.nextLine();
                        }
                        System.out.println("Ручной Human " + sizeArray);
                        break;
                    case 4:
                        System.out.println("Insert Sort");
                        break;
                    case 41:
                        System.out.println("Insert Sort нечетных классов");
                        break;
                    case 42:
                        System.out.println("Insert Sort четных классов");
                        break;
                    case 5:
                        System.out.println("Укажите какой разыскиваемый объект");
                        break;
                    case 6:
                        System.out.print("Укажите путь к файлу: ");
                        break;
                    case 7:
                        sout_menu();
                        break;
                    case 8:
                        running = false;
                        break;
                    default:
                        System.out.println("Недопустимая команда повторите ввод");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            while (running) {
                System.out.print("->");
                userCommand = scanner.nextInt();
                switch (userCommand) {
                    case 1:
                        System.out.print("Размер массива = ");
                        sizeArray = scanner.nextInt();
                        break;
                    case 10:
                        scanner.nextLine();
                        System.out.print("Укажите файл: ");
                        fileName = scanner.next();
                        //arrayList = animalFactory.fromFile(fileName, sizeArray);
                        arrayList.print();
                        break;
                    case 11:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                        }
                        System.out.println("Рандом Animal " + sizeArray);
                        //arrayList = animalFactory.fromGenerator(sizeArray);
                        arrayList.print();
                        break;
                    case 12:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                            scanner.nextLine();
                        }
                        System.out.println("Ручной Animal " + sizeArray);
                        //arrayList = animalFactory.fromConsole(scanner, sizeArray);
                        arrayList.print();
                        break;
                    case 20:
                        scanner.nextLine();
                        System.out.print("Укажите файл: ");
                        fileName = scanner.next();
                        System.out.println("Из файла " + fileName + " заполнить массив Barrel");
                        break;
                    case 21:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                        }
                        System.out.println("Рандом Barrel " + sizeArray);
                        break;
                    case 22:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                            scanner.nextLine();
                        }
                        System.out.println("Ручной Barrel " + sizeArray);
                        break;
                    case 30:
                        scanner.nextLine();
                        System.out.print("Укажите файл: ");
                        fileName = scanner.next();
                        System.out.println("Из файла " + fileName + " заполнить массив Human");
                        break;
                    case 31:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                        }
                        System.out.println("Рандом Human " + sizeArray);
                        break;
                    case 32:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                            scanner.nextLine();
                        }
                        System.out.println("Ручной Human " + sizeArray);
                        break;
                    case 4:
                        System.out.println("Insert Sort");
                        break;
                    case 41:
                        System.out.println("Insert Sort нечетных классов");
                        break;
                    case 42:
                        System.out.println("Insert Sort четных классов");
                        break;
                    case 5:
                        System.out.println("Укажите какой разыскиваемый объект");
                        break;
                    case 6:
                        System.out.print("Укажите путь к файлу: ");
                        break;
                    case 7:
                        sout_menu();
                        break;
                    case 8:
                        running = false;
                        break;
                    default:
                        System.out.println("Недопустимая команда повторите ввод");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.close();
    }
}