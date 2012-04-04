package org.alcibiade.asciiart.coord;

import java.util.Iterator;
import org.junit.Assert;
import org.junit.Test;

public class TextBoxTest {

    @Test
    public void testNew() {
        TextBox b1 = new TextBox(new TextCoord(3, 2));
        TextBox b2 = new TextBox(new TextCoord(2, 0), new TextCoord(6, 1));

        TextBox bound = new TextBox(b1, b2);
        Assert.assertEquals(new TextBox(new TextCoord(0, 0), new TextCoord(6, 2)),
                bound);
    }

    @Test
    public void testGetSize() {
        TextBox box = new TextBox(new TextCoord(3, 2), new TextCoord(6, 1));
        Assert.assertEquals(new TextBoxSize(3, 1), box.getSize());
    }

    @Test
    public void testIsInside() {
        TextBox box = new TextBox(new TextCoord(3, 2), new TextCoord(6, 1));
        Assert.assertTrue(box.isInside(new TextCoord(3, 1)));
        Assert.assertTrue(box.isInside(new TextCoord(4, 1)));
        Assert.assertTrue(!box.isInside(new TextCoord(3, 3)));
        Assert.assertTrue(!box.isInside(new TextCoord(6, 2)));
        Assert.assertTrue(!box.isInside(new TextCoord(6, 1)));
    }

    @Test
    public void testToString() {
        TextBox box = new TextBox(new TextCoord(3, 2), new TextCoord(6, 1));
        Assert.assertNotNull(box.toString());
    }

    @Test
    public void testIterator() {
        TextBox box = new TextBox(new TextCoord(5, 6), new TextCoord(3, 2));
        Iterator<TextCoord> iterator = box.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(new TextCoord(3, 2), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(new TextCoord(4, 2), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(new TextCoord(3, 3), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(new TextCoord(4, 3), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(new TextCoord(3, 4), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(new TextCoord(4, 4), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(new TextCoord(3, 5), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(new TextCoord(4, 5), iterator.next());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertNull(iterator.next());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertNull(iterator.next());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertNull(iterator.next());
    }

    @Test
    public void testEquals() {
        TextBox box = new TextBox(new TextCoord(2, 2), new TextCoord(5, 4));
        TextBox same1 = new TextBox(new TextCoord(2, 2), new TextCoord(5, 4));
        TextBox same2 = new TextBox(new TextCoord(2, 4), new TextCoord(5, 2));
        TextBox same3 = new TextBox(new TextCoord(5, 4), new TextCoord(2, 2));
        TextBox diff1 = new TextBox(new TextCoord(2, 2), new TextCoord(4, 4));
        TextBox diff2 = new TextBox(new TextCoord(2, 3), new TextCoord(5, 4));

        Assert.assertTrue(box.equals(same1));
        Assert.assertTrue(box.hashCode() == same1.hashCode());
        Assert.assertTrue(box.equals(same2));
        Assert.assertTrue(box.hashCode() == same2.hashCode());
        Assert.assertTrue(box.equals(same3));
        Assert.assertTrue(box.hashCode() == same3.hashCode());

        Assert.assertFalse(box.equals(diff1));
        Assert.assertFalse(box.equals(diff2));
    }
}
