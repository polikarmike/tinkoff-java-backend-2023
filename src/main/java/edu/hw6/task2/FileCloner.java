package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCloner {
    private static final char DOT = '.';

    private FileCloner() {

    }

    public static void cloneFile(Path path) {
        try {
            String fileName = path.getFileName().toString();
            String baseName = getBaseName(fileName);
            String extension = getFileExtension(fileName);

            int copyNumber = 1;
            Path destinationPath = path.resolveSibling(getCopyFileName(baseName, extension, copyNumber));

            while (Files.exists(destinationPath)) {
                copyNumber++;
                destinationPath = path.resolveSibling(getCopyFileName(baseName, extension, copyNumber));
            }

            Files.copy(path, destinationPath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getBaseName(String fileName) {
        int dotIndex = fileName.lastIndexOf(DOT);
        return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
    }

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(DOT);
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex);
    }

    private static String getCopyFileName(String baseName, String extension, int copyNumber) {
        if (copyNumber == 1) {
            return baseName + " — копия" + extension;
        } else {
            return baseName + " — копия (" + copyNumber + ")" + extension;
        }
    }

}

