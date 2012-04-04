package org.alcibiade.asciiart.widget;

import org.alcibiade.asciiart.coord.TextCoord;
import org.alcibiade.asciiart.raster.CharacterRaster;
import org.alcibiade.asciiart.raster.ExtensibleCharacterRaster;
import org.alcibiade.asciiart.raster.Raster;
import org.alcibiade.asciiart.raster.RasterContext;
import org.alcibiade.asciiart.widget.border.NullBorder;
import org.alcibiade.asciiart.widget.layout.BoxLayout;
import org.alcibiade.asciiart.widget.layout.GridLayout;
import org.alcibiade.asciiart.widget.model.ArrayTableModel;
import org.junit.Assert;
import org.junit.Test;

public class TextPanelTest {

    @Test
    public void testAddRemove() {
        TextPanel panel = new TextPanel(new BoxLayout());
        TextWidget widget = new TextPanel(new BoxLayout());

        panel.add(widget);
        panel.add(widget);
        panel.remove(widget);
        panel.remove(widget);

        panel.add(null);
        panel.remove(null);
    }

    @Test
    public void testPanel() {
        TextWidget label = new LabelWidget("Hello world !");

        TextWidgetContainer panel = new TextPanel(new BoxLayout(BoxLayout.Y));
        panel.add(label);
        panel.add(label);

        Raster raster = new ExtensibleCharacterRaster();

        panel.render(new RasterContext(raster));
        String rasterString = raster.toString();

        Assert.assertTrue(rasterString.startsWith("Hello world !"));
    }

    @Test
    public void testPanelWithBordersBox() {
        String[][] tableData = {{"A"}};

        TextWidgetContainer panel = new TextPanel(new BoxLayout(BoxLayout.X));
        panel.add(new TableWidget(new ArrayTableModel(tableData)));
        panel.add(new TableWidget(new ArrayTableModel(tableData)));

        CharacterRaster raster = new ExtensibleCharacterRaster();
        panel.render(new RasterContext(raster));

        Assert.assertEquals('A', (char) raster.getCharacter(new TextCoord(2, 1)));
        Assert.assertEquals('A', (char) raster.getCharacter(new TextCoord(7, 1)));
    }

    @Test
    public void testPanelWithBordersGrid() {
        String[][] tableData = {{"A"}};

        TextWidgetContainer panel = new TextPanel(new GridLayout(2));
        panel.add(new TableWidget(new ArrayTableModel(tableData)));
        panel.add(new TableWidget(new ArrayTableModel(tableData)));

        CharacterRaster raster = new ExtensibleCharacterRaster();
        panel.render(new RasterContext(raster));

        Assert.assertEquals('A', (char) raster.getCharacter(new TextCoord(2, 1)));
        Assert.assertEquals('A', (char) raster.getCharacter(new TextCoord(7, 1)));
    }

    @Test
    public void testPanelWithTransparentBorder() {
        String[][] tableData = {{"A"}};

        TextWidgetContainer panel = new TextPanel(new GridLayout(2),
                new NullBorder(0, 0, 1, 0, 0, 0));
        panel.add(new TableWidget(new ArrayTableModel(tableData)));
        panel.add(new TableWidget(new ArrayTableModel(tableData)));

        CharacterRaster raster = new ExtensibleCharacterRaster();
        panel.render(new RasterContext(raster));
        Assert.assertEquals('A', (char) raster.getCharacter(new TextCoord(2, 1)));
        Assert.assertEquals('A', (char) raster.getCharacter(new TextCoord(8, 1)));
    }

    @Test
    public void testImbricated() {
        String[][] tableData = {{"A"}};

        TextWidgetContainer panel = new TextPanel(new GridLayout(2),
                new NullBorder(0, 0, 1, 0, 0, 0));
        panel.add(new TableWidget(new ArrayTableModel(tableData)));

        TextWidgetContainer panelR = new TextPanel(new BoxLayout(BoxLayout.Y));

        panelR.add(new TableWidget(new ArrayTableModel(tableData)));
        panelR.add(new TableWidget(new ArrayTableModel(tableData)));
        panel.add(panelR);

        CharacterRaster raster = new ExtensibleCharacterRaster();
        panel.render(new RasterContext(raster));
        Assert.assertEquals('A', (char) raster.getCharacter(new TextCoord(2, 1)));
        Assert.assertEquals('A', (char) raster.getCharacter(new TextCoord(8, 1)));
        Assert.assertEquals('A', (char) raster.getCharacter(new TextCoord(8, 4)));
    }
}
