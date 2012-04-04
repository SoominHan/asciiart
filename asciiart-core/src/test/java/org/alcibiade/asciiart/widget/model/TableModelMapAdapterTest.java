package org.alcibiade.asciiart.widget.model;

import java.util.Map;
import java.util.TreeMap;
import org.junit.Assert;
import org.junit.Test;

public class TableModelMapAdapterTest {

    @Test
    public void testMap() {
        Map<Integer, String> map = new TreeMap<Integer, String>();
        map.put(4, "A");
        map.put(8, "B");
        map.put(15, "C");
        map.put(16, "D");
        map.put(23, "E");
        map.put(42, "F");

        TableModel model = new TableModelMapAdapter(map);
        Assert.assertEquals(2, model.getWidth());
        Assert.assertEquals(6, model.getHeight());
        Assert.assertEquals("4", model.getCellContent(0, 0));
        Assert.assertEquals("8", model.getCellContent(0, 1));
        Assert.assertEquals("15", model.getCellContent(0, 2));
        Assert.assertEquals("16", model.getCellContent(0, 3));
        Assert.assertEquals("23", model.getCellContent(0, 4));
        Assert.assertEquals("42", model.getCellContent(0, 5));
        Assert.assertEquals("A", model.getCellContent(1, 0));
        Assert.assertEquals("B", model.getCellContent(1, 1));
        Assert.assertEquals("C", model.getCellContent(1, 2));
        Assert.assertEquals("D", model.getCellContent(1, 3));
        Assert.assertEquals("E", model.getCellContent(1, 4));
        Assert.assertEquals("F", model.getCellContent(1, 5));
        Assert.assertNull(model.getColumnTitle(0));
        Assert.assertNull(model.getColumnTitle(1));
    }

    @Test
    public void testTitle() {
        Map<Integer, String> map = new TreeMap<Integer, String>();
        TableModel model = new TableModelMapAdapter(map, "Key", "Value");
        Assert.assertEquals("Key", model.getColumnTitle(0));
        Assert.assertEquals("Value", model.getColumnTitle(1));
    }
}
