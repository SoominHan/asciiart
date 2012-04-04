package org.alcibiade.asciiart.widget.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class TableModelCollectionAdapterTest {

    @Test
    public void testSet() {
        Set<Integer> set = new HashSet<Integer>();
        set.add(4);
        set.add(8);
        set.add(15);
        set.add(16);
        set.add(23);
        set.add(42);

        TableModel model = new TableModelCollectionAdapter(set);
        Assert.assertEquals(1, model.getWidth());
        Assert.assertEquals(6, model.getHeight());
        Assert.assertNull(model.getColumnTitle(0));
    }

    @Test
    public void testList() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(4);
        list.add(8);
        list.add(15);
        list.add(16);
        list.add(23);
        list.add(42);

        TableModel model = new TableModelCollectionAdapter(list);
        Assert.assertEquals(1, model.getWidth());
        Assert.assertEquals(6, model.getHeight());
        Assert.assertEquals("4", model.getCellContent(0, 0));
        Assert.assertEquals("8", model.getCellContent(0, 1));
        Assert.assertEquals("15", model.getCellContent(0, 2));
        Assert.assertEquals("16", model.getCellContent(0, 3));
        Assert.assertEquals("23", model.getCellContent(0, 4));
        Assert.assertEquals("42", model.getCellContent(0, 5));
        Assert.assertNull(model.getColumnTitle(0));
    }

    @Test
    public void testTitle() {
        List<Integer> list = new ArrayList<Integer>();
        TableModel model = new TableModelCollectionAdapter(list, "My title");
        Assert.assertEquals("My title", model.getColumnTitle(0));
    }
}
