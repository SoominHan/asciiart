package org.alcibiade.asciiart.widget;

import java.awt.image.BufferedImage;
import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.image.rasterize.Rasterizer;
import org.alcibiade.asciiart.raster.RasterContext;

public class PictureWidget extends TextWidget {

    private TextBoxSize size;
    private BufferedImage image;
    private Rasterizer rasterizer;

    public PictureWidget(TextBoxSize size, BufferedImage image, Rasterizer rasterizer) {
        this.size = size;
        this.image = image;
        this.rasterizer = rasterizer;
    }

    @Override
    public TextBoxSize getSize() {
        return size;
    }

    @Override
    public void render(RasterContext rc) {
        rasterizer.rasterize(image, rc, size);
    }
}
