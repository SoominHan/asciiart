package org.alcibiade.asciiart.coord;

public class TextBoxSize extends TextCoord {

    public TextBoxSize(TextCoord coord) {
        this(coord.getX(), coord.getY());
    }

    public TextBoxSize(int x, int y) {
        super(x, y);
        if (x < 0 || y < 0) {
            throw new TextCoordException(String.format(
                    "Inconsistent box size: %d,%d", x, y));
        }
    }
}
