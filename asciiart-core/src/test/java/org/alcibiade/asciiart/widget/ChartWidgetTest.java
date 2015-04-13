package org.alcibiade.asciiart.widget;

import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.raster.ExtensibleCharacterRaster;
import org.alcibiade.asciiart.raster.Raster;
import org.alcibiade.asciiart.raster.RasterContext;
import org.alcibiade.asciiart.widget.model.CurveModel;
import org.junit.Test;

public class ChartWidgetTest {

    @Test
    public void testChartWidget() {
        CurveModel curveModel = new CurveModel() {

            @Override
            public Double getValue(double x) {
                return Math.sin(x);
            }
        };

        ChartWidget widget = new ChartWidget(new TextBoxSize(80, 12), curveModel, 0, 10);
        Raster raster = new ExtensibleCharacterRaster();

        widget.render(new RasterContext(raster));
        System.out.println(raster);
    }
}
