package org.CustomClass.factory;

import org.CustomArrayList;
import org.CustomClass.Animal;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public interface FactoryStrategy<T> {
    List<T> fromFile(String fileName, int size) throws IOException, ClassNotFoundException;
    List<T> fromGenerator(int size);
    List<Animal> fromConsole(Scanner scanner, int size);
}
