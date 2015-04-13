package org.alcibiade.asciiart.raster;

import org.alcibiade.asciiart.coord.TextCoord;
import org.alcibiade.asciiart.widget.LabelWidget;
import org.alcibiade.asciiart.widget.TextWidget;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ExtensibleCharacterRasterTest {

    @Test
    public void testLabelWithExtensibleRaster() {
        TextWidget widget = new LabelWidget("Hello world !");
        Raster raster = new ExtensibleCharacterRaster();

        RasterContext rc = new RasterContext(raster);
        widget.render(rc);
        String rasterString = raster.toString();

        Assertions.assertThat(rasterString).startsWith("Hello world !");
    }

    @Test
    public void testIssueWithDefaultValue() {
        Raster raster = new ExtensibleCharacterRaster(' ');
        RasterContext rc = new RasterContext(raster);

        rc.drawCharacter(new TextCoord(10, 0), 'x');

        String rasterString = raster.toString();

        Assertions.assertThat(rasterString).startsWith("    ");
    }
}
