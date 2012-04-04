package org.alcibiade.asciiart.widget;

import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.coord.TextCoord;
import org.alcibiade.asciiart.raster.RasterContext;

public class LabelWidget extends TextWidget {

    private String text;

    public LabelWidget(String text) {
        this.text = text;
    }

    @Override
    public void render(RasterContext rc) {
        rc.drawString(TextCoord.ORIGIN, text);
    }

    @Override
    public TextBoxSize getSize() {
        return new TextBoxSize(text.length(), 1);
    }
}
