package org.alcibiade.asciiart.raster;

import org.alcibiade.asciiart.coord.TextCoord;

public interface Raster {

    void setCharacter(TextCoord coord, char c);
}
