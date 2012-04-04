package org.alcibiade.asciiart.widget.border;

import org.alcibiade.asciiart.coord.TextBox;
import org.alcibiade.asciiart.raster.RasterContext;

public interface WidgetBorder {

    int getMarginTop();

    int getMarginBottom();

    int getMarginLeft();

    int getMarginRight();

    int getOverlapH();

    int getOverlapV();

    void render(RasterContext rc, TextBox box);
}
