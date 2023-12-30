package edu.project4.variations;

import edu.project4.Types.Point;

public class PolarVariation implements Variation {
    @Override
    public void applyToPoint(Point point) {
        double x2 = point.x * point.x;
        double y2 = point.y * point.y;
        double r = Math.sqrt(x2 + y2);
        double theta = Math.atan2(point.y, point.x);

        point.x =  theta / Math.PI;
        point.y = r - 1;
    }
}
