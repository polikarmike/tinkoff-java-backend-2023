package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Проверка валидного варианта")
    void testFixStringValidInput() {
        // given
        String text = "hTsii  s aimex dpus rtni.g";

        // when
        String fixText = Task4.fixString(text);

        // then
        assertThat(fixText)
            .isEqualTo("This is a mixed up string.");

    }

    @Test
    @DisplayName("Проверка строки с четным количеством символов")
    void testFixStringEvenLength() {
        // given
        String text = "123456";

        // when
        String fixText = Task4.fixString(text);

        // then
        assertThat(fixText)
            .isEqualTo("214365");
    }

    @Test
    @DisplayName("Проверка строки с нечетным количеством символов")
    void testFixStringOddLength() {
        // given
        String text = "badce";

        // when
        String fixText = Task4.fixString(text);

        // then
        assertThat(fixText)
            .isEqualTo("abcde");
    }

    @Test
    @DisplayName("Проверка пустой строки")
    void testFixStringEmptyInput() {
        // given
        String text = "";

        // when
        String fixText = Task4.fixString(text);

        // then
        assertThat(fixText)
            .isEmpty();
    }

    @Test
    @DisplayName("Проверка null-строки")
    void testFixStringNullInput() {
        // given
        String text = null;

        // when
        String fixText = Task4.fixString(text);

        // then
        assertThat(fixText)
            .isNull();
    }

}
