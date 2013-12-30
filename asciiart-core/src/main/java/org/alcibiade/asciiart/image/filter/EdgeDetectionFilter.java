package org.alcibiade.asciiart.image.filter;

import java.awt.image.BufferedImage;

/**
 * Detect edges in image.
 *
 * @author Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */
public class EdgeDetectionFilter implements ImageFilter {

    private static final double[] SOBEL_H = {1, 0, -1, 2, 0, -2, 1, 0, -1};
    private static final double[] SOBEL_V = {1, 2, 1, 0, 0, 0, -1, -2, -1};
    private double filterRatio;

    public EdgeDetectionFilter() {
        this(2.0);
    }

    public EdgeDetectionFilter(double filterRatio) {
        this.filterRatio = filterRatio;
    }

    @Override
    public BufferedImage filter(BufferedImage image) {
        int targetWidth = image.getWidth();
        int targetHeight = image.getHeight();

        BufferedImage edgesImage = new BufferedImage(targetWidth, targetHeight,
                BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                double convolutionH = convolution(image, x, y, SOBEL_H);
                double convolutionV = convolution(image, x, y, SOBEL_V);
                double result = Math.sqrt(convolutionH * convolutionH + convolutionV * convolutionV);
                // System.out.printf("%f x %f -> %f\n", convolutionH, convolutionV, result);

                int resultLevel = 255 - (int) (255 * filterRatio * result);
                resultLevel = Math.max(0, resultLevel);
                resultLevel = Math.min(255, resultLevel);
                edgesImage.setRGB(x, y, resultLevel << 16 | resultLevel << 8 | resultLevel);
            }
        }

        return edgesImage;
    }

    private double convolution(BufferedImage image, int x, int y, double[] matrix) {
        assert matrix.length == 9;
        assert image.getWidth() > 1;
        assert image.getHeight() > 1;

        double sumValues = 0;
        double sumRatios = 0;

        // Delta x/y coordinates
        for (int dy = -1; dy < 2; dy++) {
            for (int dx = -1; dx < 2; dx++) {
                // Actual image coordinates
                int ax = x + dx;
                int ay = y + dy;

                // Adjust border collisions in mirror mode
                if (ax < 0) {
                    ax = 1;
                }

                if (ax >= image.getWidth()) {
                    ax = image.getWidth() - 2;
                }

                if (ay < 0) {
                    ay = 1;
                }

                if (ay >= image.getHeight()) {
                    ay = image.getHeight() - 2;
                }

                // Now do the actual computing...
                double ratio = matrix[3 * (dy + 1) + dx + 1];

                int value = image.getRGB(ax, ay) & 0xFF;
                sumValues += ratio * value / 255.;
                sumRatios += Math.abs(ratio);
            }
        }

        return sumValues / sumRatios;
    }
}
