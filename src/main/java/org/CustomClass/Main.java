package org.CustomClass;

import org.CustomClass.SortStrategy.SortStrategy;
import org.CustomClass.factory.AnimalFactory;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static void sout_menu(){
        System.out.println("1 - Указать размер массива");
        System.out.println("10 - Заполнить массив Animal с файла");
        System.out.println("\t11 - Заполнить массив Animal случайно");
        System.out.println("\t12 - Заполнить массив Animal вручную");
        System.out.println("20 - Заполнить массив Barrel с файла");
        System.out.println("\t21 - Заполнить массив Barrel случайно");
        System.out.println("\t22 - Заполнить массив Barrel вручную");
        System.out.println("30 - Заполнить массив Human с файла");
        System.out.println("\t31 - Заполнить массив Human случайно");
        System.out.println("\t32 - Заполнить массив Human вручную\n");

        System.out.println("4 - Отсортировать массив");
        System.out.println("\t41 - Отсортировать нечётные классы массива");
        System.out.println("\t42 - Отсортировать чётные классы массива");
        System.out.println("5 - Поиск нужного класса в массиве");
        System.out.println("6 - Записать массив в файл");
        System.out.println("7 - Вывести массив в консоль");


        System.out.println("8 - показать список комманд");
        System.out.println("9 - Выход");
        System.out.print("Готов к вводу");
    }

    public static void main(String[] args) {
        boolean running = true;
        int userCommand;
        int currentClass = 0;
        Scanner scanner = new Scanner(System.in);

        int sizeArray = 0;
        AnimalFactory animalFactory = new AnimalFactory();
        String fileName;
        List<Animal> animalList = null;
        List<Human> humanList = null;

        System.out.println("Введите команду:");
        sout_menu();

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
                        animalList = animalFactory.fromFile(fileName, sizeArray);
                        System.out.println(animalList);
                        currentClass = 1;
                        break;
                    case 11:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                        }
                        animalList = animalFactory.fromGenerator(sizeArray);
                        System.out.println(animalList.toString());
                        currentClass = 1;
                        break;
                    case 12:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                        }
                        System.out.println("Ручной Animal " + sizeArray);
                        animalList = animalFactory.fromConsole(scanner, sizeArray);
                        System.out.println(animalList);
                        currentClass = 1;
                        break;
                    case 20:
                        scanner.nextLine();
                        System.out.print("Укажите файл: ");
                        fileName = scanner.next();
                        System.out.println("Из файла " + fileName + " заполнить массив Barrel");
                        currentClass = 2;
                        break;
                    case 21:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                        }
                        System.out.println("Рандом Barrel " + sizeArray);
                        currentClass = 2;
                        break;
                    case 22:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                            scanner.nextLine();
                        }
                        System.out.println("Ручной Barrel " + sizeArray);
                        currentClass = 2;
                        break;
                    case 30:
                        scanner.nextLine();
                        System.out.print("Укажите файл: ");
                        fileName = scanner.next();
                        System.out.println("Из файла " + fileName + " заполнить массив Human");
                        currentClass = 3;
                        break;
                    case 31:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                        }
                        System.out.println("Рандом Human " + sizeArray);
                        currentClass = 3;
                        break;
                    case 32:
                        if (sizeArray == 0) {
                            System.out.print("Укажите размер массива = ");
                            sizeArray = scanner.nextInt();
                            scanner.nextLine();
                        }
                        System.out.println("Ручной Human " + sizeArray);
                        currentClass = 3;
                        break;

                    case 4:
                        switch (currentClass){
                            case 1:
                                SortStrategy<Animal> sortStrategy = new SortStrategy<>();
                                sortStrategy.sortStr(animalList, Animal::getSpecies);
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Заполните массив");
                                break;
                        }
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
                        if (animalList == null){
                            System.out.println("Задайте массив для его ввывода");
                            break;
                        }
                        System.out.println(animalList);
                        break;
                    case 8:
                        sout_menu();
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
}