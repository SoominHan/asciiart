package org.alcibiade.asciiart.raster;

import org.alcibiade.asciiart.widget.LabelWidget;
import org.alcibiade.asciiart.widget.TextWidget;
import org.junit.Assert;
import org.junit.Test;

public class ExtensibleCharacterRasterTest {

    @Test
    public void testLabelWithExtensibleRaster() {
        TextWidget widget = new LabelWidget("Hello world !");
        Raster raster = new ExtensibleCharacterRaster();

        RasterContext rc = new RasterContext(raster);
        widget.render(rc);
        String rasterString = raster.toString();

        Assert.assertTrue(rasterString.startsWith("Hello world !"));
    }
}
