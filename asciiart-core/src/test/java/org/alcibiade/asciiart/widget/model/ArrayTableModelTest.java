package org.alcibiade.asciiart.widget.model;

import org.junit.Assert;
import org.junit.Test;

public class ArrayTableModelTest {

    @Test
    public void testNew() {
        String data[][] = new String[3][2];
        new ArrayTableModel(data);
    }

    @Test
    public void testSize() {
        String data[][] = new String[3][2];
        TableModel model = new ArrayTableModel(data);
        Assert.assertEquals(3, model.getWidth());
        Assert.assertEquals(2, model.getHeight());
    }

    @Test
    public void testEmpty() {
        String data[][] = new String[0][0];
        TableModel model = new ArrayTableModel(data);
        Assert.assertEquals(0, model.getWidth());
        Assert.assertEquals(0, model.getHeight());
    }
}
