package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class project3Test {
    @Test
    @DisplayName("Создания файла-статистики в формате Markdown")
    public void testMarkdownFile() throws IOException {
        // Given
        String outputPath = "src/main/resources/projects3logs/markdown_report.md";
        String[] args = {"--path", "src/test/java/edu/resources/project3/logs/2015_1_logs.txt", "--format", "markdown"};

        // When
        NginxLogAnalyzer.analyze(args);

        // Then
        assertTrue(Files.exists(Path.of(outputPath)));
        List<String> lines = Files.readAllLines(Path.of(outputPath));
        assertEquals("| Файл(-ы) | `2015_1_logs.txt` |", lines.get(4));
    }

    @Test
    @DisplayName("Создания файла-статистики в формате AsciiDoc")
    public void testAdocFileCreation() throws IOException {
        // Given
        String outputPath = "src/main/resources/projects3logs/adoc_report.adoc";
        String[] args = {"--path", "src/test/java/edu/resources/project3/logs/2015_1_logs.txt", "--format", "adoc"};

        // When
        NginxLogAnalyzer.analyze(args);

        // Then
        assertTrue(Files.exists(Path.of(outputPath)));
        List<String> lines = Files.readAllLines(Path.of(outputPath));
        assertEquals("^| Файл(-ы) >| `2015_1_logs.txt`", lines.get(4));
    }

    @Test
    @DisplayName("Создания файла-статистики в формате Markdown из URL")
    public void testMarkdownFileFromURL() throws IOException {
        // Given
        String outputPath = "src/main/resources/projects3logs/markdown_report.md";
        String[] args = {"--path",
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
            "--format", "markdown"};

        // When
        NginxLogAnalyzer.analyze(args);

        // Then
        assertTrue(Files.exists(Path.of(outputPath)));
        List<String> lines = Files.readAllLines(Path.of(outputPath));
        assertEquals(
            "| Файл(-ы) | `https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs` |",
            lines.get(4)
        );
    }

    @Test
    @DisplayName("Использование шаблона локального пути")
    public void testLocalPathTemplateForMarkdownFile() throws IOException {
        // Given
        String outputPath = "src/main/resources/projects3logs/markdown_report.md";
        String[] args = {"--path", "src/test/java/edu/resources/project3/logs/**/2015*", "--format", "markdown"};

        // When
        NginxLogAnalyzer.analyze(args);

        // Then
        assertTrue(Files.exists(Path.of(outputPath)));
        List<String> lines = Files.readAllLines(Path.of(outputPath));
        assertEquals("| Файл(-ы) | `2015_2_logs.txt`, `2015_3_logs.txt` |", lines.get(4));
    }

    @DisplayName("Создание файла-статистики с ограничениями по дате")
    @Test public void testDataLimitForAdocFileCreation() throws IOException {
        // Given
        String outputPath = "src/main/resources/projects3logs/adoc_report.adoc";
        String[] args =
            {"--path", "src/test/java/edu/resources/project3/logs/2015_1_logs.txt", "--from", "2015-05-25", "--to",
                "2015-06-01", "--format", "adoc"};

        // When
        NginxLogAnalyzer.analyze(args);

        // Then
        assertTrue(Files.exists(Path.of(outputPath)));
        List<String> lines = Files.readAllLines(Path.of(outputPath));
        assertEquals("^| Начальная дата >| 2015-05-25", lines.get(5));
        assertEquals("^| Конечная дата >| 2015-06-01", lines.get(6));
    }

    @Test
    @DisplayName("Создание файла-статистики с несуществующим путем")
    public void nonExstingPath() throws IOException {
        // Given
        String outputPath = "src/main/resources/projects3logs/adoc_report.adoc";
        String[] args = {"--path", "nonexistingpath", "--format", "adoc"};

        // When
        NginxLogAnalyzer.analyze(args);

        // Then
        assertTrue(Files.exists(Path.of(outputPath)));
        List<String> lines = Files.readAllLines(Path.of(outputPath));
        assertEquals("^| Файл(-ы) >| `-`", lines.get(4));

    }
}
