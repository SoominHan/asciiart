package org.alcibiade.asciiart.coord;

/**
 * Project from an interval to another one with constant ratio.
 *
 * @author Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */
public class CoordProjection {

    private double srcMin, srcMax;
    private double dstMin, dstMax;

    public CoordProjection(double srcMin, double srcMax, double dstMin, double dstMax) {
        this.srcMin = srcMin;
        this.srcMax = srcMax;
        this.dstMin = dstMin;
        this.dstMax = dstMax;
    }

    public double project(double srcValue) {
        double ratio = (srcValue - srcMin) / (srcMax - srcMin);
        double dstValue = dstMin + ratio * (dstMax - dstMin);
        return dstValue;
    }

}
