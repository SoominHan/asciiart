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

        for (int y = (int) sectionArea.getMinY(); y < sectionArea.getMaxY(); y++) {
            for (int x = (int) sectionArea.getMinX(); x < sectionArea.getMaxX(); x++) {
                int rgb = image.getRGB(x, y);
                int light = rgb & 0xFF;
                totalLight += light;
                totalSamples += 1;
            }
        }

        return totalLight / totalSamples / 255.;
    }
}
