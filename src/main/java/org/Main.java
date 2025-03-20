package org;

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
        int user_command;
        int size_array = 0;
        boolean status_prog = true;
        String file_name;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите команду:");
        sout_menu();

        while(status_prog){
            System.out.print("->");
            user_command = scanner.nextInt();
            switch (user_command){
                case 1:
                    System.out.print("Размер массива = ");
                    size_array = scanner.nextInt();
                    break;
                case 10:
                    scanner.nextLine();
                    System.out.print("Укажите файл: ");
                    file_name = scanner.next();
                    CustomArrayList<String> animal = new CustomArrayList<>(size_array);
                    animal.add("Cat");
                    System.out.println("Из файла " + file_name + " заполнить массив Animal " + animal.get(0));
                    break;
                case 11:
                    if (size_array == 0) {
                        System.out.print("Укажите размер массива = ");
                        size_array = scanner.nextInt();
                    }
                    System.out.println("Рандом Animal " + size_array);
                    break;
                case 12:
                    if (size_array == 0) {
                        System.out.print("Укажите размер массива = ");
                        size_array = scanner.nextInt();
                    }
                    System.out.println("Ручной Animal " + size_array);
                    break;
                case 20:
                    scanner.nextLine();
                    System.out.print("Укажите файл: ");
                    file_name = scanner.next();
                    System.out.println("Из файла " + file_name + " заполнить массив Barrel");
                    break;
                case 21:
                    if (size_array == 0) {
                        System.out.print("Укажите размер массива = ");
                        size_array = scanner.nextInt();
                    }
                    System.out.println("Рандом Barrel " + size_array);
                    break;
                case 22:
                    if (size_array == 0) {
                        System.out.print("Укажите размер массива = ");
                        size_array = scanner.nextInt();
                    }
                    System.out.println("Ручной Barrel " + size_array);
                    break;
                case 30:
                    scanner.nextLine();
                    System.out.print("Укажите файл: ");
                    file_name = scanner.next();
                    System.out.println("Из файла " + file_name + " заполнить массив Human");
                    break;
                case 31:
                    if (size_array == 0) {
                        System.out.print("Укажите размер массива = ");
                        size_array = scanner.nextInt();
                    }
                    System.out.println("Рандом Human " + size_array);
                    break;
                case 32:
                    if (size_array == 0) {
                        System.out.print("Укажите размер массива = ");
                        size_array = scanner.nextInt();
                    }
                    System.out.println("Ручной Human " + size_array);
                    break;
                case 4:
                    System.out.println("Insert Sort");
                    break;
                case 41:
                    System.out.println("Insert Sort нечёиных классов");
                    break;
                case 42:
                    System.out.println("Insert Sort чёиных классов");
                    break;
                case 5:
                    System.out.println("Указжите какой разыскуемы объект");
                    break;
                case 6:
                    System.out.print("Укажите путь к файлу: ");
                    break;
                case 7:
                    sout_menu();
                    break;
                case 8:
                    status_prog = false;
                    break;
                default:
                    System.out.println("Недопустимая команнда повторите ввод");
                    break;
            }
        }
        scanner.close();
    }
}