package org.alcibiade.asciiart.raster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.alcibiade.asciiart.coord.TextBox;
import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.coord.TextCoord;

public class CharacterRaster implements Raster, Iterable<String> {

    public static final char DEFAULT_CHARACTER = '.';
    private char fillChar = '.';
    protected char[][] buffer;

    public CharacterRaster(TextBoxSize size) {
        this(size, DEFAULT_CHARACTER);
    }

    public CharacterRaster(TextBoxSize size, char fillChar) {
        assert size.getX() > 0;
        assert size.getY() > 0;
        this.fillChar = fillChar;
        buffer = allocateRaster(size);
    }

    public TextBoxSize getSize() {
        return new TextBoxSize(buffer.length, buffer[0].length);
    }

    @Override
    public void setCharacter(TextCoord coord, char c) {
        TextBox box = new TextBox(getSize());
        if (box.isInside(coord)) {
            buffer[coord.getX()][coord.getY()] = c;
        }
    }

    public Character getCharacter(TextCoord coord) {
        Character result = null;

        TextBox box = new TextBox(getSize());
        if (box.isInside(coord)) {
            result = buffer[coord.getX()][coord.getY()];
        }

        return result;
    }

    protected final char[][] allocateRaster(TextBoxSize size) {
        char[][] localBuffer = new char[size.getX()][size.getY()];

        for (int x = 0; x < size.getX(); x++) {
            Arrays.fill(localBuffer[x], fillChar);
        }

        return localBuffer;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        TextBox box = new TextBox(getSize());
        for (TextCoord coord : box) {
            builder.append(buffer[coord.getX()][coord.getY()]);

            if (coord.getX() == getSize().getX() - 1) {
                builder.append('\n');
            }
        }

        return builder.toString();
    }

    @Override
    public Iterator<String> iterator() {
        List<String> lines = new ArrayList<String>();

        StringBuilder builder = new StringBuilder();

        TextBox box = new TextBox(getSize());
        for (TextCoord coord : box) {
            builder.append(buffer[coord.getX()][coord.getY()]);

            if (coord.getX() == getSize().getX() - 1) {
                lines.add(builder.toString());
                builder.setLength(0);
            }
        }

        return lines.iterator();
    }
}
