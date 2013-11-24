package org.alcibiade.asciiart.image.rasterize;

import java.awt.image.BufferedImage;

/**
 * Tool used to comput lightness of a specific section of a bitmap image.
 *
 * @author Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */
public class ImageComparator {

    public long difference(BufferedImage image1, BufferedImage image2) {
        long totalDelta = 0;

        for (int y = 0; y < image1.getHeight(); y++) {
            for (int x = 0; x < image1.getWidth(); x++) {
                int rgb1 = image1.getRGB(x, y);
                int light1 = rgb1 & 0xFF;
                int rgb2 = image2.getRGB(x, y);
                int light2 = rgb2 & 0xFF;

                int delta = Math.abs(light1 - light2);
                totalDelta += delta;
            }
        }

        return totalDelta;
    }
}
