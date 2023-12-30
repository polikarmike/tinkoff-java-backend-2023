package edu.project4.variations;

import edu.project4.Types.Point;

public class SinusoidalVariation implements Variation {
    @Override
    public void applyToPoint(Point point) {
        point.x = Math.sin(point.x);
        point.y = Math.sin(point.y);
    }
}
