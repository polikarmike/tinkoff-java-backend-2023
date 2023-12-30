package edu.project4;

import edu.project4.variations.DiskVariation;
import edu.project4.variations.HeartVariation;
import edu.project4.variations.PolarVariation;
import edu.project4.variations.SinusoidalVariation;
import edu.project4.variations.SphericalVariation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import edu.project4.Types.Point ;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class VariationsTest {
    @Test
    @DisplayName("Проверка работы вариации диск")
    void diskVariationTest() {
        // Given
        DiskVariation diskVariation = new DiskVariation();
        Point point = new Point(1.0, 2.0);

        // When
        diskVariation.applyToPoint(point);

        // Then
        assertNotEquals(1.0, point.x);
        assertNotEquals(2.0, point.y);
    }

    @Test
    @DisplayName("Проверка работы вариации сердце")
    void heartVariationTest() {
        // Given
        HeartVariation heartVariation = new HeartVariation();
        Point point = new Point(1.0, 2.0);

        // When
        heartVariation.applyToPoint(point);

        // Then
        assertNotEquals(1.0, point.x);
        assertNotEquals(2.0, point.y);
    }

    @Test
    @DisplayName("Проверка работы сферической вариации")
    void sphericalVariationTest() {
        // Given
        SphericalVariation sphericalVariation = new SphericalVariation();
        Point point = new Point(1.0, 2.0);

        // When
        sphericalVariation.applyToPoint(point);

        // Then
        assertNotEquals(1.0, point.x);
        assertNotEquals(2.0, point.y);
    }

    @Test
    @DisplayName("Проверка работы вариации синусоида")
    void sinusoidalVariationTest() {
        // Given
        SinusoidalVariation sinusoidalVariation = new SinusoidalVariation();
        Point point = new Point(1.0, 2.0);

        // When
        sinusoidalVariation.applyToPoint(point);

        // Then
        assertNotEquals(1.0, point.x);
        assertNotEquals(2.0, point.y);
    }

    @Test
    @DisplayName("Проверка работы полярной вариации")
    void polarVariationTest() {
        // Given
        PolarVariation polarVariation = new PolarVariation();
        Point point = new Point(1.0, 2.0);

        // When
        polarVariation.applyToPoint(point);

        // Then
        assertNotEquals(1.0, point.x);
        assertNotEquals(2.0, point.y);
    }
}
