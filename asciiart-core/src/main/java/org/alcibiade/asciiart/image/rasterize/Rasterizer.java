package org.alcibiade.asciiart.image.rasterize;

import java.awt.image.BufferedImage;
import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.raster.RasterContext;

/**
 * A rasterizer renders an RGB image to a character raster.
 *
 * @author Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */
public interface Rasterizer {

    void rasterize(BufferedImage image, RasterContext rc, TextBoxSize size);
}
