package org.alcibiade.asciiart.widget;

import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.raster.RasterContext;

public abstract class TextWidget {

    public abstract TextBoxSize getSize();

    public abstract void render(RasterContext rc);
}
