package org.alcibiade.asciiart.widget.border;

import org.alcibiade.asciiart.coord.TextCoord;
import org.alcibiade.asciiart.raster.CharacterRaster;
import org.alcibiade.asciiart.raster.ExtensibleCharacterRaster;
import org.alcibiade.asciiart.raster.RasterContext;
import org.alcibiade.asciiart.widget.LabelWidget;
import org.alcibiade.asciiart.widget.TextPanel;
import org.alcibiade.asciiart.widget.layout.BoxLayout;
import org.alcibiade.asciiart.widget.layout.LayoutManager;
import org.junit.Assert;
import org.junit.Test;

public class NullBorderTest {

    @Test
    public void testNewEmpty() {
        WidgetBorder zero = new NullBorder();
        Assert.assertEquals(0, zero.getMarginTop());
        Assert.assertEquals(0, zero.getMarginLeft());
        Assert.assertEquals(0, zero.getMarginRight());
        Assert.assertEquals(0, zero.getMarginBottom());
        Assert.assertEquals(0, zero.getOverlapH());
        Assert.assertEquals(0, zero.getOverlapV());
    }

    @Test
    public void testNewMargins() {
        WidgetBorder zero = new NullBorder(1, 2, 3, 4, 5, 6);
        Assert.assertEquals(1, zero.getMarginTop());
        Assert.assertEquals(2, zero.getMarginLeft());
        Assert.assertEquals(3, zero.getMarginRight());
        Assert.assertEquals(4, zero.getMarginBottom());
        Assert.assertEquals(5, zero.getOverlapH());
        Assert.assertEquals(6, zero.getOverlapV());
    }

    @Test
    public void testZeroMargin() {
        WidgetBorder border = new NullBorder();
        LayoutManager layout = new BoxLayout(BoxLayout.X);
        TextPanel panel = new TextPanel(layout, border);
        panel.add(new LabelWidget("a"));
        panel.add(new LabelWidget("b"));

        CharacterRaster raster = new ExtensibleCharacterRaster();
        panel.render(new RasterContext(raster));
        Assert.assertTrue(raster.toString().startsWith("ab"));
    }

    @Test
    public void testMargin() {
        WidgetBorder border = new NullBorder(1, 2, 2, 1, 1, 1);
        LayoutManager layout = new BoxLayout(BoxLayout.X);
        TextPanel panel = new TextPanel(layout, border);
        panel.add(new LabelWidget("a"));
        panel.add(new LabelWidget("b"));

        CharacterRaster raster = new ExtensibleCharacterRaster();
        panel.render(new RasterContext(raster));
        Assert.assertEquals('a', (char) raster.getCharacter(new TextCoord(2, 1)));
        Assert.assertEquals('b', (char) raster.getCharacter(new TextCoord(6, 1)));
    }
}