package edu.hw6.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OutputStreamCompositionTest {

    @Test
    @DisplayName("Запись в файл, используя OutputStreamComposition")
    void writeTestStringToFileUsingOutputChain() throws IOException {
        // Given
        String testString = "Programming is learned by writing programs. ― Brian Kernighan";
        Path tempFilePath = Paths.get("src/test/java/edu/hw6/task4/file.txt");
        var expectedResult = List.of(testString);

        // When
        OutputStreamComposition.writeToOutputStreamChain(tempFilePath, testString);

        // Then
        var lines = Files.readAllLines(tempFilePath);
        assertThat(lines).isEqualTo(expectedResult);

        // Clean Up
        Files.deleteIfExists(tempFilePath);
    }
}
