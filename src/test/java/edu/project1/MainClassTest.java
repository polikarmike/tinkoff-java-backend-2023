package edu.project1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;


public class MainClassTest {

        @Test
        @DisplayName("Проверка Запуска")
        public void testRun_ShouldNotThrowException() {
            // Given
            String input = "GIVE UP\nno\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            // When
            Exception exception = null;
            try {
                Main.main(new String[]{});
            } catch (Exception e) {
                exception = e;
            }

            // Then
            assertNull(exception, "Method main() threw an exception");
        }
}
