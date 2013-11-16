package org.alcibiade.asciiart.image.rasterize;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import org.alcibiade.asciiart.coord.TextBox;
import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.coord.TextCoord;
import org.alcibiade.asciiart.raster.RasterContext;

/**
 * Behaviour shared by rasterizer implementations.
 *
 * @author Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */
public abstract class AbstractLinearRasterizer implements Rasterizer {

    private ImageSectionLightnessExtractor lightnessExtractor = new ImageSectionLightnessExtractor();

    public ImageSectionLightnessExtractor getLightnessExtractor() {
        return lightnessExtractor;
    }

    @Override
    public void rasterize(BufferedImage image, RasterContext rc, TextBoxSize size) {
        
        TextBox box = new TextBox(size);
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        for (TextCoord coord : box) {
            Rectangle sectionArea = new Rectangle(
                    coord.getX() * imageWidth / size.getX(),
                    coord.getY() * imageHeight / size.getY(),
                    imageWidth / size.getX(),
                    imageHeight / size.getY());

            char c = rasterizeSection(image, sectionArea);
            rc.drawCharacter(coord, c);
        }
    }

    protected abstract char rasterizeSection(BufferedImage image, Rectangle sectionArea);
}
