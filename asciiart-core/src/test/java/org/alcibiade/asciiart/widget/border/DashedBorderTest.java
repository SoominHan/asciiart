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

public class DashedBorderTest {

    @Test
    public void testRendering() {
        WidgetBorder border = new DashedBorder();
        LayoutManager layout = new BoxLayout(BoxLayout.X);
        TextPanel panel = new TextPanel(layout, border);
        panel.add(new LabelWidget("a"));
        panel.add(new LabelWidget("b"));

        CharacterRaster raster = new ExtensibleCharacterRaster();
        panel.render(new RasterContext(raster));

        Assert.assertEquals('a', (char) raster.getCharacter(new TextCoord(2, 1)));
        Assert.assertEquals('b', (char) raster.getCharacter(new TextCoord(6, 1)));
        Assert.assertEquals('+', (char) raster.getCharacter(new TextCoord(0, 0)));
        Assert.assertEquals('+', (char) raster.getCharacter(new TextCoord(0, 2)));
        Assert.assertEquals('+', (char) raster.getCharacter(new TextCoord(4, 2)));
        Assert.assertEquals('+', (char) raster.getCharacter(new TextCoord(8, 2)));
        Assert.assertEquals('-', (char) raster.getCharacter(new TextCoord(1, 0)));
        Assert.assertEquals('-', (char) raster.getCharacter(new TextCoord(2, 0)));
        Assert.assertEquals('-', (char) raster.getCharacter(new TextCoord(3, 0)));
        Assert.assertEquals('|', (char) raster.getCharacter(new TextCoord(0, 1)));
        Assert.assertEquals('|', (char) raster.getCharacter(new TextCoord(4, 1)));
    }
}