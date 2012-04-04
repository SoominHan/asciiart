package org.alcibiade.asciiart.widget;

import java.awt.image.BufferedImage;
import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.raster.RasterContext;
import org.alcibiade.asciiart.raster.image.ImageRasterizer;

public class PictureWidget extends TextWidget {

    private TextBoxSize size;
    private BufferedImage image;

    public PictureWidget(TextBoxSize size, BufferedImage image) {
        this.size = size;
        this.image = image;
    }

    @Override
    public TextBoxSize getSize() {
        return size;
    }

    @Override
    public void render(RasterContext rc) {
        ImageRasterizer.rasterize(image, rc, size);
    }
}
