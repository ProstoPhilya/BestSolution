package org.CustomClass.Saved;

import java.io.IOException;

public interface Savable {
    void saveToFile(String fileName) throws IOException;
}
