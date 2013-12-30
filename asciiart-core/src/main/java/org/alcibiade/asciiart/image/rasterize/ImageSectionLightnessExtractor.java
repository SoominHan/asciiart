package org.alcibiade.asciiart.image.rasterize;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Tool used to comput lightness of a specific section of a bitmap image.
 *
 * @author Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */
public class ImageSectionLightnessExtractor {

    public double getLightness(BufferedImage image, Rectangle sectionArea) {
        long totalLight = 0;
        long totalSamples = 0;

        int maxX = Math.min((int) sectionArea.getMaxX() + 1, image.getWidth());
        int maxY = Math.min((int) sectionArea.getMaxY() + 1, image.getHeight());

        for (int y = (int) sectionArea.getMinY(); y < maxY; y++) {
            for (int x = (int) sectionArea.getMinX(); x < maxX; x++) {
                int rgb = image.getRGB(x, y);
                int light = rgb & 0xFF;
                totalLight += light;
                totalSamples += 1;
            }
        }

        return totalLight / totalSamples / 255.;
    }
}
