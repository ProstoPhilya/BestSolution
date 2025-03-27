package org.owlTeam;

import org.owlTeam.entityClass.Basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public final class SaveToFile {
    public static void save(String fileName, CustomArrayList<Basic> arrayList) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName, true))) {
            for (Basic element: arrayList) {
                out.writeObject(element);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
