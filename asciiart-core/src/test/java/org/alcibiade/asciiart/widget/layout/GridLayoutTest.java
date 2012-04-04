package org.alcibiade.asciiart.widget.layout;

import java.util.ArrayList;
import java.util.List;
import org.alcibiade.asciiart.coord.TextBox;
import org.alcibiade.asciiart.widget.LabelWidget;
import org.alcibiade.asciiart.widget.TextWidget;
import org.alcibiade.asciiart.widget.border.NullBorder;
import org.junit.Assert;
import org.junit.Test;

public class GridLayoutTest {

    @Test
    public void testEmpty() {
        LayoutManager layout = new GridLayout();

        ArrayList<TextWidget> widgets = new ArrayList<TextWidget>();
        List<TextBox> coords = layout.layout(widgets, new NullBorder());
        Assert.assertEquals(0, coords.size());
    }

    @Test
    public void testColumns() {
        LayoutManager layout = new GridLayout(3);
        TextWidget label = new LabelWidget("Hello");

        ArrayList<TextWidget> widgets = new ArrayList<TextWidget>();
        widgets.add(label);
        widgets.add(label);
        widgets.add(label);
        widgets.add(label);

        List<TextBox> coords = layout.layout(widgets, new NullBorder());
        Assert.assertEquals(4, coords.size());
        Assert.assertEquals(new TextBox(0, 0, 5, 1), coords.get(0));
        Assert.assertEquals(new TextBox(5, 0, 10, 1), coords.get(1));
        Assert.assertEquals(new TextBox(10, 0, 15, 1), coords.get(2));
        Assert.assertEquals(new TextBox(0, 1, 5, 2), coords.get(3));
    }

    @Test
    public void testColumnsWithBorder() {
        LayoutManager layout = new GridLayout(3);
        TextWidget label = new LabelWidget("Hello");

        ArrayList<TextWidget> widgets = new ArrayList<TextWidget>();
        widgets.add(label);
        widgets.add(label);
        widgets.add(label);
        widgets.add(label);

        List<TextBox> coords = layout.layout(widgets, new NullBorder(1, 1, 1,
                1, 1, 1));
        Assert.assertEquals(4, coords.size());
        Assert.assertEquals(new TextBox(1, 1, 6, 2), coords.get(0));
        Assert.assertEquals(new TextBox(7, 1, 12, 2), coords.get(1));
        Assert.assertEquals(new TextBox(13, 1, 18, 2), coords.get(2));
        Assert.assertEquals(new TextBox(1, 3, 6, 4), coords.get(3));
    }
}
