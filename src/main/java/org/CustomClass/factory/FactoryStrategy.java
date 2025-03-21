package org.CustomClass.factory;

import org.CustomArrayList;
import org.CustomClass.Animal;

import java.io.IOException;
import java.util.Scanner;

public interface FactoryStrategy<T> {
    CustomArrayList<T> fromFile(String fileName, int size) throws IOException, ClassNotFoundException;
    CustomArrayList<T> fromGenerator(int size);
    CustomArrayList<Animal> fromConsole(Scanner scanner, int size);
}
