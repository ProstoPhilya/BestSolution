package org.owlTeam.entityClass.factory;

import org.owlTeam.CustomArrayList;

import java.io.IOException;
import java.util.Scanner;

public interface FactoryStrategy<T> {
    CustomArrayList<T> fromFile(String fileName, int size) throws IOException, ClassNotFoundException;
    CustomArrayList<T> fromGenerator(int size);
    CustomArrayList<T> fromConsole(Scanner scanner, int size);
}
