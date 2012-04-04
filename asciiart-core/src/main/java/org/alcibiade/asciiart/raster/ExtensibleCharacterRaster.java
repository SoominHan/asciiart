package org.alcibiade.asciiart.raster;

import org.alcibiade.asciiart.coord.TextBox;
import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.coord.TextCoord;

public class ExtensibleCharacterRaster extends CharacterRaster {

    public ExtensibleCharacterRaster() {
        this(DEFAULT_CHARACTER);
    }

    public ExtensibleCharacterRaster(char fillChar) {
        this(new TextBoxSize(1, 1), fillChar);
    }

    public ExtensibleCharacterRaster(TextBoxSize size, char fillChar) {
        super(size, fillChar);
    }

    @Override
    public void setCharacter(TextCoord coord, char c) {
        TextBox box = new TextBox(getSize());
        if (!box.isInside(coord)) {
            grow(coord);
        }

        super.setCharacter(coord, c);
    }

    private void grow(TextCoord coord) {
        TextBoxSize size = getSize();
        TextBoxSize newSize = new TextBoxSize(size.maxValues(coord.add(TextCoord.ONE)));
        char[][] localBuffer = allocateRaster(newSize);
        for (TextCoord c : new TextBox(size)) {
            localBuffer[c.getX()][c.getY()] = buffer[c.getX()][c.getY()];
        }

        buffer = localBuffer;
    }
}
