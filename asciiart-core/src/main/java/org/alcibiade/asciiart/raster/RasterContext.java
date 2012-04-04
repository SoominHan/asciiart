package org.alcibiade.asciiart.raster;

import org.alcibiade.asciiart.coord.TextCoord;

public class RasterContext {

    private TextCoord offset;
    private Raster raster;

    public RasterContext(Raster raster) {
        this.raster = raster;
        this.offset = new TextCoord(0, 0);
    }

    public void translate(TextCoord coord) {
        offset = offset.add(coord);
    }

    public void drawCharacter(TextCoord coord, Character c) {
        raster.setCharacter(offset.add(coord), c);
    }

    public void drawString(TextCoord coord, CharSequence text) {
        for (int i = 0; i < text.length(); i++) {
            drawCharacter(coord.add(new TextCoord(i, 0)), text.charAt(i));
        }
    }
}
