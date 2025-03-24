package org.owlTeam;

import org.owlTeam.entityClass.Animal;
import org.owlTeam.entityClass.Basic;
import org.owlTeam.SortStrategy.SortStrategy;
import org.owlTeam.entityClass.factory.AnimalFactory;

import java.util.Scanner;

public class Main {
    private static CustomArrayList<Basic> arrayList = null;
    private static CustomArrayList<Basic> binarySearchResult = new CustomArrayList<>();

    private static void soutMenu(){
        System.out.println("0 - Вывести массив и найденные элементы");
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
        int sizeArray = 0;
        boolean running = true;
        String defaultPath = "src/main/resources/";
        String fileName;
        Scanner scanner = new Scanner(System.in);
        AnimalFactory animalFactory = new AnimalFactory();

        System.out.println("Введите команду:");
        soutMenu();

        try {
            while (running) {
                System.out.print("->");
                userCommand = scanner.nextInt();
                switch (userCommand){
                    case 0:
                        System.out.println("Статус: ");
                        status();
                        break;
                    case 1:
                        System.out.print("Размер массива = ");
                        sizeArray = scanner.nextInt();
                        scanner.nextLine();
                        break;
                    case 10:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                            scanner.nextLine();
                        }
                        System.out.print("Укажите файл: ");
                        fileName = scanner.next();
                        arrayList = animalFactory.fromFile(fileName, sizeArray);
                        status();
                        break;
                    case 11:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                            scanner.nextLine();
                        }
                        arrayList = animalFactory.fromGenerator(sizeArray);
                        status();
                        break;
                    case 12:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                            scanner.nextLine();
                        }
                        arrayList = animalFactory.fromConsole(scanner, sizeArray);
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
                        }
                        System.out.println("Ручной Barrel " + sizeArray);
                        break;
                    case 30:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                            scanner.nextLine();
                        }
                        System.out.print("Укажите файл: ");
                        fileName = scanner.next();
                        break;
                    case 31:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                            scanner.nextLine();
                        }
                        System.out.println("Рандом Human " + sizeArray);
                        break;
                    case 32:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                        }
                        System.out.println("Ручной Human " + sizeArray);
                        break;
                    case 4:
                        System.out.println("Insert Sort");
                        if (arrayList != null && arrayList.isNotEmpty()) {
                            SortStrategy.sort(arrayList);
                        }
                        status();
                        break;
                    case 41:
                        System.out.println("Insert Sort нечетных классов");
                        break;
                    case 42:
                        System.out.println("Insert Sort четных классов");
                        if (arrayList != null && arrayList.isNotEmpty()) {
                            SortStrategy.sortEven(arrayList, Basic::getIntValue);
                        }
                        status();
                        break;
                    case 5:
                        System.out.println("Поиск объекта");
                        scanner.nextLine();
                        if (arrayList.isNotEmpty()) {
                            switch (arrayList.get(0)) {
                                case Animal a:
                                    Basic searchElement = animalFactory.fromConsole(scanner, 1).get(0);
                                    Basic element = BinarySearch.search(arrayList, searchElement);
                                    if (element != null) {
                                        System.out.println("Найден запрашиваемый объект: ");
                                        System.out.println(element);
                                        binarySearchResult.add(element);
                                    } else {
                                        System.out.println("Запрашиваемый объект не найден");
                                    }

                                    break;
                                default: break;
                            }
                        }
                        break;
                    case 6:
                        System.out.print("Укажите файл: ");
                        fileName = scanner.next();
                        if (arrayList.isNotEmpty()) {
                            SaveToFile.save(fileName, arrayList);
                        }
                        break;
                    case 7:
                        System.out.print("Укажите файл: ");
                        fileName = scanner.next();
                        if (binarySearchResult.isNotEmpty()) {
                            SaveToFile.save(fileName, binarySearchResult);
                            binarySearchResult.clear();
                        }
                        break;
                    case 8:
                        soutMenu();
                        break;
                    case 9:
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

    public static void status() {
        if (arrayList != null && arrayList.isNotEmpty()) {
            System.out.println("Содержимое списка: ");
            arrayList.println();
        }
        if (binarySearchResult != null && binarySearchResult.isNotEmpty()) {
            System.out.println("Найденные элементы: ");
            binarySearchResult.println();
        }
    }
}