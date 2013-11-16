package org.alcibiade.asciiart.image.rasterize;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Basic Dash rasterizer.
 *
 * @author Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */
public class DashRasterizer extends AbstractLinearRasterizer {

    @Override
    protected char rasterizeSection(BufferedImage image, Rectangle sectionArea) {
        double lightness = getLightnessExtractor().getLightness(image, sectionArea);
        return lightness > 0.5 ? ' ' : '#';
    }
}
