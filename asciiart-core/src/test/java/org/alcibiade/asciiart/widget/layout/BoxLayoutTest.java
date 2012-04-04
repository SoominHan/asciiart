package org.alcibiade.asciiart.widget.layout;

import java.util.ArrayList;
import java.util.List;
import org.alcibiade.asciiart.coord.TextBox;
import org.alcibiade.asciiart.coord.TextCoord;
import org.alcibiade.asciiart.raster.CharacterRaster;
import org.alcibiade.asciiart.raster.ExtensibleCharacterRaster;
import org.alcibiade.asciiart.raster.RasterContext;
import org.alcibiade.asciiart.widget.LabelWidget;
import org.alcibiade.asciiart.widget.TableWidget;
import org.alcibiade.asciiart.widget.TextPanel;
import org.alcibiade.asciiart.widget.TextWidget;
import org.alcibiade.asciiart.widget.border.NullBorder;
import org.alcibiade.asciiart.widget.model.TableModelCollectionAdapter;
import org.junit.Assert;
import org.junit.Test;

public class BoxLayoutTest {

    @Test
    public void testEmpty() {
        LayoutManager layout = new BoxLayout(BoxLayout.X);

        ArrayList<TextWidget> widgets = new ArrayList<TextWidget>();
        List<TextBox> coords = layout.layout(widgets, new NullBorder());
        Assert.assertEquals(0, coords.size());
    }

    @Test
    public void testX() {
        LayoutManager layout = new BoxLayout(BoxLayout.X);
        TextWidget label = new LabelWidget("Hello");

        ArrayList<TextWidget> widgets = new ArrayList<TextWidget>();
        widgets.add(label);
        widgets.add(label);

        List<TextBox> coords = layout.layout(widgets, new NullBorder());
        Assert.assertEquals(2, coords.size());
        Assert.assertEquals(new TextBox(TextCoord.ORIGIN, label.getSize()), coords.get(0));
        Assert.assertEquals(new TextBox(new TextCoord(label.getSize().getX(), 0),
                new TextCoord(2 * label.getSize().getX(), label.getSize().getY())), coords.get(1));
    }

    @Test
    public void testY() {
        LayoutManager layout = new BoxLayout(BoxLayout.Y);
        TextWidget label = new LabelWidget("Hello");

        ArrayList<TextWidget> widgets = new ArrayList<TextWidget>();
        widgets.add(label);
        widgets.add(label);

        List<TextBox> coords = layout.layout(widgets, new NullBorder());
        Assert.assertEquals(2, coords.size());
        Assert.assertEquals(new TextBox(TextCoord.ORIGIN, label.getSize()), coords.get(0));
        Assert.assertEquals(new TextBox(new TextCoord(0, label.getSize().getY()),
                new TextCoord(label.getSize().getX(), 2 * label.getSize().getY())), coords.get(1));
    }

    @Test
    public void testTables() {
        List<Integer> l = new ArrayList<Integer>();
        l.add(1);
        l.add(2);
        l.add(3);

        TextPanel panel = new TextPanel();
        TableWidget table1 = new TableWidget(
                new TableModelCollectionAdapter(l), true);
        TableWidget table2 = new TableWidget(
                new TableModelCollectionAdapter(l), true);
        panel.add(table1);
        panel.add(table2);

        CharacterRaster raster = new ExtensibleCharacterRaster();
        panel.render(new RasterContext(raster));
        Assert.assertEquals('1', (char) raster.getCharacter(new TextCoord(2, 1)));
        Assert.assertEquals('1', (char) raster.getCharacter(new TextCoord(2, 6)));
    }
}
