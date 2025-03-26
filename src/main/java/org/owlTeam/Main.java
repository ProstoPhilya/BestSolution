package org.owlTeam;

import org.owlTeam.entityClass.Animal;
import org.owlTeam.entityClass.Basic;
import org.owlTeam.entityClass.Human;
import org.owlTeam.entityClass.factory.AnimalFactory;
import org.owlTeam.entityClass.factory.HumanFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static CustomArrayList<Basic> arrayList = null;
    private static Basic binarySearchResult = null;

    private static void outMenu(){
        System.out.println("0 - Выход");
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
        System.out.println("6 - Записать массив в файл");
        System.out.println("6 - Записать результат бинарного поиска в файл");


        System.out.println("7 - Вывести массив и найденные элементы");
        System.out.println("8 - показать список комманд");
        System.out.print("Готов к вводу");
    }

    public static void main(String[] args) {
        int userCommand;
        int sizeArray = 0;
        boolean running = true;

        String fileName;
        String defaultPathForAnimal = "src/main/resources/AnimalFiles/";
        String defaultPathForBarrel = "src/main/resources/BarrelFiles/";
        String defaultPathForHuman = "src/main/resources/HumanFiles/";
        Scanner scanner = new Scanner(System.in);

        AnimalFactory animalFactory = new AnimalFactory();
        //BarrelFactory barrelFactory = new BarrelFactory();
        HumanFactory humanFactory = new HumanFactory();

        System.out.println("Введите команду:");
        outMenu();

            while (running) {
                try {
                System.out.print("->");
                userCommand = scanner.nextInt();
                scanner.nextLine();
                    switch (userCommand){
                    case 0:
                        running = false;
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
                        fileName = defaultPathForAnimal + scanner.next();
                        arrayList = animalFactory.fromFile(fileName, sizeArray);
                        sizeArray = arrayList.size();
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
                        arrayList = humanFactory.fromFile(defaultPathForHuman + fileName, sizeArray);
                        status();
                        break;
                    case 31:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                            scanner.nextLine();
                        }
                        arrayList = humanFactory.fromGenerator(sizeArray);
                        status();
                        break;
                    case 32:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                        }
                        arrayList = humanFactory.fromConsole(scanner,sizeArray);
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
                        if (arrayList != null && arrayList.isNotEmpty()) {
                            SortStrategy.sortByCondition(arrayList,x -> x % 2 != 0, Basic::getIntValue);
                        }
                        status();
                        break;
                    case 42:
                        System.out.println("Insert Sort четных классов");
                        if (arrayList != null && arrayList.isNotEmpty()) {
                            SortStrategy.sortByCondition(arrayList,x -> x % 2 == 0, Basic::getIntValue);
                        }
                        status();
                        break;
                    case 5:
                        if (arrayList != null && arrayList.isNotEmpty()) {
                            Basic searchElement = null;
                                switch (arrayList.get(0)) {
                                    case Animal e:
                                        System.out.println("Задайте искомое животное");
                                        searchElement = animalFactory.fromConsole(scanner, 1).get(0);
                                        Basic element = BinarySearch.search(arrayList, searchElement);
                                        if (element != null) {
                                            System.out.println("Найден запрашиваемый объект: ");
                                            System.out.println(element);
                                            //binarySearchResult.add(element);
                                        } else {
                                            System.out.println("Запрашиваемый объект не найден");
                                        }
                                        break;
//                                case Barel e:
//                                System.out.println("Задайте искомое животное");
//                                Basic searchElement = animalFactory.fromConsole(scanner, 1).get(0);
//                                    break;
                                    case Human e:
                                        System.out.println("Задайте искомого человека");
                                        searchElement = humanFactory.fromConsole(scanner, 1).get(0);
                                        break;
                                    default: break;
                                }
                        }else System.out.println("Массив пуст, заполните массив");
                        break;
                    case 6:
                        if (arrayList != null && arrayList.isNotEmpty()) {
                            System.out.print("Укажите файл: ");
                            fileName = scanner.next();
                            switch (arrayList.get(0)){
                                case Animal e:
                                    SaveToFile.save(defaultPathForAnimal + fileName, arrayList);
                                    break;
                                case Human e:
                                    SaveToFile.save(defaultPathForHuman + fileName, arrayList);
                                    break;
                                default:
                                    System.out.println("Этот тип не включён");
                                    break;
                            }
                        } else System.out.println("Массив пуст, заполните массив");
                        break;
                    case 7:
                        System.out.println("Статус: ");
                        status();
                        break;
                    case 8:
                        outMenu();
                        break;
                    case 9:
                        break;
                    default:
                        System.out.println("Недопустимая команда повторите ввод");
                        break;
                }
                } catch (InputMismatchException e) {
                    System.out.println("Введён некоректный формат команды.\n" +
                            "Используйте целые числа!");
                    scanner.nextLine();
                } catch (Exception e) {
                    e.printStackTrace();
            }
        }
        scanner.close();
    }

    public static void status() {
        if (arrayList != null && arrayList.isNotEmpty()) {
            System.out.println("Содержимое списка: ");
            arrayList.println();
        }
        if (binarySearchResult != null) {
            System.out.println("Найденные элементы:\n" + binarySearchResult);
        }
    }
}