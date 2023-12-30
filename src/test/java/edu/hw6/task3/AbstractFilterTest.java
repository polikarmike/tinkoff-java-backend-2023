package edu.hw6.task3;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;


class AbstractFilterTest {

    @Test
    @DisplayName("Использование цепочки фильтров")
    void useFilterChain() throws IOException {
        // Given
        Path testDirPath = Path.of("src/test/java/edu/hw6/task3/dataexamples");

        AbstractFilter regularFile = Files::isRegularFile;

        DirectoryStream.Filter<Path> filter = regularFile
            .and(AbstractFilter.readable())
            .and(AbstractFilter.largerThan(50_000))
            .and(AbstractFilter.magicNumber((byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G'))
            .and(AbstractFilter.globMatches("*.png"))
            .and(AbstractFilter.regexContains("[-]"));

        // When & Then
        try (var entries = Files.newDirectoryStream(testDirPath, filter)) {
            Assertions.assertThat(entries).containsExactly(testDirPath.resolve("tinkoff-bank.png"));
        }
    }
}
