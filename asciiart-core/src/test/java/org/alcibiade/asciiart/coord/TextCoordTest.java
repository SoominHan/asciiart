package org.alcibiade.asciiart.coord;

import org.junit.Assert;
import org.junit.Test;

public class TextCoordTest {

    @Test
    public void testNew() {
        new TextCoord(3, 2);
    }

    @Test
    public void testXY() {
        TextCoord coord = new TextCoord(3, 2);
        Assert.assertEquals(3, coord.getX());
        Assert.assertEquals(2, coord.getY());
    }

    @Test
    public void testAdd() {
        TextCoord coord1 = new TextCoord(3, 2);
        Assert.assertEquals(coord1, coord1.add(TextCoord.ORIGIN));
        Assert.assertEquals(new TextCoord(5, 8), coord1.add(new TextCoord(2, 6)));
    }

    @Test
    public void testSubtract() {
        TextCoord coord1 = new TextCoord(5, 8);
        Assert.assertEquals(coord1, coord1.subtract(TextCoord.ORIGIN));
        Assert.assertEquals(new TextCoord(3, 2), coord1.subtract(new TextCoord(2, 6)));
    }

    @Test
    public void testInvert() {
        TextCoord coord1 = new TextCoord(5, 8);
        Assert.assertEquals(new TextCoord(-5, -8), coord1.invert());
    }

    @Test
    public void testEquals() {
        TextCoord coord1 = new TextCoord(3, 2);
        TextCoord coord2 = new TextCoord(3, 2);
        Assert.assertEquals(coord1, coord2);
        Assert.assertEquals(coord1.hashCode(), coord2.hashCode());
    }

    @Test
    public void testComparable() {
        TextCoord coord1 = new TextCoord(3, 2);
        TextCoord coord2 = new TextCoord(4, 2);
        Assert.assertTrue(coord1.compareTo(coord2) < 0);
        Assert.assertTrue(coord1.compareTo(coord1) == 0);
        Assert.assertTrue(coord2.compareTo(coord1) > 0);
    }
}
