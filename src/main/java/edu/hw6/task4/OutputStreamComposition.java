package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;


public class OutputStreamComposition {
    private OutputStreamComposition() {

    }

    public static void writeToOutputStreamChain(Path filePath, String text) throws IOException {
        try (
            OutputStream fileOutputStream = Files.newOutputStream(filePath, StandardOpenOption.CREATE);
            CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new CRC32());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
            PrintWriter printWriter = new PrintWriter(outputStreamWriter)
        ) {
            printWriter.println(text);
        }
    }
}

