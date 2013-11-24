package org.alcibiade.asciiart.image.rasterize;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.raster.CharacterRaster;
import org.alcibiade.asciiart.raster.ExtensibleCharacterRaster;
import org.alcibiade.asciiart.raster.RasterContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageRasterizerTest {

    private Logger logger = LoggerFactory.getLogger(ImageRasterizerTest.class);

    @Test
    public void testPictureRasterization() throws IOException {
        String[] images = {
            "org/alcibiade/asciiart/image/samples/Sample_LetterA.png",
            "org/alcibiade/asciiart/image/samples/Sample_Circle.png",
            "org/alcibiade/asciiart/image/samples/Sample_BlackCircle.png",
            "org/alcibiade/asciiart/image/samples/Sample_Gradient_H.png",
            "org/alcibiade/asciiart/image/samples/Sample_Gradient_V.png",
            "org/alcibiade/asciiart/image/samples/Sample_Line.png"
        };

        Rasterizer[] rasterizers = {
            new DashRasterizer(),
            new ShapeRasterizer()
        };

        for (String image : images) {
            for (Rasterizer rasterizer : rasterizers) {
                rasterizePicture(image, rasterizer);
            }
        }
    }

    private void rasterizePicture(String imageFileName, Rasterizer rasterizer) throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL imageUrl = classLoader.getResource(imageFileName);
        BufferedImage image = ImageIO.read(imageUrl);

        CharacterRaster raster = new ExtensibleCharacterRaster();
        rasterizer.rasterize(image, new RasterContext(raster), new TextBoxSize(80, 24));

        if (logger.isDebugEnabled()) {
            logger.debug("Image: {}", imageFileName);
            logger.debug("Rasterizer: {} \n{}", rasterizer, raster.toString());
        }
    }
}