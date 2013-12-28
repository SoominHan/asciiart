package org.alcibiade.asciiart.image.rasterize;

import java.awt.image.BufferedImage;
import java.io.IOException;
import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.raster.CharacterRaster;
import org.alcibiade.asciiart.raster.ExtensibleCharacterRaster;
import org.alcibiade.asciiart.raster.RasterContext;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LightImageRasterizeTest {

    private Logger logger = LoggerFactory.getLogger(LightImageRasterizeTest.class);

    /**
     * Check that almost light images are not rendered entierly empty but that delta is propagated across characters.
     *
     * @throws IOException
     */
    @Test
    public void testPictureRasterization() throws IOException {

        BufferedImage image = new BufferedImage(320, 200, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                image.setRGB(x, y, 0xFBFBFB);
            }
        }

        Rasterizer rasterizer = new ShapeRasterizer();

        CharacterRaster raster = new ExtensibleCharacterRaster();
        rasterizer.rasterize(image, new RasterContext(raster), new TextBoxSize(80, 24));

        if (logger.isDebugEnabled()) {
            logger.debug("Rasterizer: {} \n{}", rasterizer, raster.toString());
        }

        Assert.assertTrue(raster.toString().trim().length() > 0);
    }
}
