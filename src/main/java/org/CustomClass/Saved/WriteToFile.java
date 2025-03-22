package org.CustomClass.Saved;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteToFile {
    public static void writeDataToFile(String fileName, String data) throws IOException {
        Path path = Paths.get(fileName);

        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        try (FileWriter writer = new FileWriter(path.toFile(), true)) {
            writer.write(data);
        }
    }

    public static void save(Savable savableObject, String fileName) throws IOException {
        savableObject.saveToFile(fileName);
    }
}
