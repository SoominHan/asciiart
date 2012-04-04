package org.alcibiade.asciiart.raster.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.raster.CharacterRaster;
import org.alcibiade.asciiart.raster.ExtensibleCharacterRaster;
import org.alcibiade.asciiart.raster.RasterContext;
import org.junit.Test;

public class ImageRasterizerTest {

    @Test
    public void testPictureRasterization() throws IOException {
        rasterizePicture("org/alcibiade/asciiart/raster/image/Sample_BlackCircle.png");
        rasterizePicture("org/alcibiade/asciiart/raster/image/Sample_Gradient_H.png");
        rasterizePicture("org/alcibiade/asciiart/raster/image/Sample_Gradient_V.png");
    }

    private void rasterizePicture(String imageFileName) throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL circleImageURL = classLoader.getResource(imageFileName);
        BufferedImage circleImage = ImageIO.read(circleImageURL);

        CharacterRaster raster = new ExtensibleCharacterRaster();
        ImageRasterizer.rasterize(circleImage, new RasterContext(raster),
                new TextBoxSize(32, 10));

        // System.out.println(raster);
    }
}
