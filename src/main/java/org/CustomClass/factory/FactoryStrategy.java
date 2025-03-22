package org.CustomClass.factory;

import org.execution.CustomArrayList;
import org.CustomClass.Animal;

import java.io.IOException;
import java.util.Scanner;

public interface FactoryStrategy<T> {
    CustomArrayList<T> fromFile(String fileName, int size) throws IOException, ClassNotFoundException;
    CustomArrayList<T> fromGenerator(int size);
    CustomArrayList<T> fromConsole(Scanner scanner, int size);
}
