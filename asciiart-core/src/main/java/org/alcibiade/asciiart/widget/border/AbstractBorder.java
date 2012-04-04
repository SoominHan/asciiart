package org.alcibiade.asciiart.widget.border;

import org.alcibiade.asciiart.coord.TextBox;
import org.alcibiade.asciiart.raster.RasterContext;

public abstract class AbstractBorder implements WidgetBorder {

    private int marginTop, marginLeft, marginRight, marginBottom;
    private int overlapH, overlapV;

    public AbstractBorder(int marginTop, int marginLeft, int marginRight,
            int marginBottom, int overlapH, int overlapV) {
        this.marginTop = marginTop;
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
        this.marginBottom = marginBottom;
        this.overlapH = overlapH;
        this.overlapV = overlapV;
    }

    @Override
    public int getMarginTop() {
        return marginTop;
    }

    @Override
    public int getMarginLeft() {
        return marginLeft;
    }

    @Override
    public int getMarginRight() {
        return marginRight;
    }

    @Override
    public int getMarginBottom() {
        return marginBottom;
    }

    @Override
    public int getOverlapH() {
        return overlapH;
    }

    @Override
    public int getOverlapV() {
        return overlapV;
    }

    @Override
    public void render(RasterContext rc, TextBox box) {
        // Do nothing
    }
}
