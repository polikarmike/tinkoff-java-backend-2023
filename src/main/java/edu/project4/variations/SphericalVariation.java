package edu.project4.variations;

import edu.project4.Types.Point;

public class SphericalVariation implements Variation {
    @Override
    public void applyToPoint(Point point) {
        double r = point.x * point.x + point.y * point.y;
        point.x = point.x / r;
        point.y = point.y / r;
    }
}
