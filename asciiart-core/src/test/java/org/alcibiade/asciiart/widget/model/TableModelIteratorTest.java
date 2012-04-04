package org.alcibiade.asciiart.widget.model;

import java.util.Iterator;
import org.junit.Assert;
import org.junit.Test;

public class TableModelIteratorTest {

    @Test
    public void testIterator() {
        String[][] contents = {{"A", "1"}, {"B", "2"}};
        TableModel model = new ArrayTableModel(contents);
        Iterator<String> iterator = model.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("A", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("B", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("1", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("2", iterator.next());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertNull(iterator.next());
    }

    @Test
    public void testIteratorOnEmptyModel() {
        String[][] contents = {};
        TableModel model = new ArrayTableModel(contents);
        Iterator<String> iterator = model.iterator();
        Assert.assertFalse(iterator.hasNext());
        Assert.assertNull(iterator.next());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertNull(iterator.next());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertNull(iterator.next());
    }
}
