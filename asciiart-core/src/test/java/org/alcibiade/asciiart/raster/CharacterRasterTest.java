package org.alcibiade.asciiart.raster;

import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.coord.TextCoord;
import org.junit.Assert;
import org.junit.Test;

public class CharacterRasterTest {

    @Test
    public void testConstructor() {
        new CharacterRaster(new TextBoxSize(4, 3));
    }

    @Test
    public void testSize() {
        CharacterRaster raster = new CharacterRaster(new TextBoxSize(4, 3));
        Assert.assertEquals(new TextBoxSize(4, 3), raster.getSize());
    }

    @Test
    public void testWrite() {
        CharacterRaster raster = new CharacterRaster(new TextBoxSize(4, 3));
        raster.setCharacter(new TextCoord(2, 2), 'a');
        raster.setCharacter(new TextCoord(3, 2), 'b');
        raster.setCharacter(new TextCoord(6, 2), 'c');
        raster.setCharacter(new TextCoord(1, -1), 'd');
    }

    @Test
    public void testToString() {
        Raster raster = new CharacterRaster(new TextBoxSize(3, 2));
        raster.setCharacter(new TextCoord(0, 0), 'a');
        raster.setCharacter(new TextCoord(1, 0), 'b');
        raster.setCharacter(new TextCoord(2, 0), 'c');
        raster.setCharacter(new TextCoord(0, 1), '1');
        raster.setCharacter(new TextCoord(1, 1), '2');
        raster.setCharacter(new TextCoord(2, 1), '3');
        Assert.assertEquals("abc\n123\n", raster.toString());
    }
}
