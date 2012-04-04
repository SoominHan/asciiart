package org.alcibiade.asciiart.widget;

import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.raster.CharacterRaster;
import org.alcibiade.asciiart.raster.Raster;
import org.alcibiade.asciiart.raster.RasterContext;
import org.junit.Assert;
import org.junit.Test;

public class LabelWidgetTest {

    @Test
    public void testNew() {
        new LabelWidget("Hello world !");
    }

    @Test
    public void testLabel() {
        TextWidget widget = new LabelWidget("Hello world !");
        Raster raster = new CharacterRaster(new TextBoxSize(40, 3));

        RasterContext rc = new RasterContext(raster);
        widget.render(rc);
        String rasterString = raster.toString();

        Assert.assertTrue(rasterString.startsWith("Hello world !"));
    }
}
