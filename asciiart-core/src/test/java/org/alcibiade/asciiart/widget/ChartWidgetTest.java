package org.alcibiade.asciiart.widget;

import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.raster.ExtensibleCharacterRaster;
import org.alcibiade.asciiart.raster.Raster;
import org.alcibiade.asciiart.raster.RasterContext;
import org.alcibiade.asciiart.widget.model.CurveModel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChartWidgetTest {

    private Logger logger = LoggerFactory.getLogger(ChartWidgetTest.class);

    @Test
    public void testChartSine() {
        renderCurve(new CurveModel() {

            @Override
            public Double getValue(double x) {
                return Math.cos(x - 1);
            }

            @Override
            public String toString() {
                return "f(x) = cos(x-1)";
            }

        });

    }

    @Test
    public void testChartWithNans() {
        renderCurve(new CurveModel() {

            @Override
            public Double getValue(double x) {
                return 1 / (x - 1);
            }

            @Override
            public String toString() {
                return "f(x) = 1 / (x-1)";
            }

        });
    }

    @Test
    public void testChartWithWideY() {
        renderCurve(new CurveModel() {

            @Override
            public Double getValue(double x) {
                return Math.pow(Math.cos(x), 6);
            }

            @Override
            public String toString() {
                return "f(x) = cos(x)^6";
            }

        });
    }


    private void renderCurve(CurveModel curveModel) {
        ChartWidget widget = new ChartWidget(new TextBoxSize(80, 12), curveModel, -1, 10);
        Raster raster = new ExtensibleCharacterRaster(' ');

        widget.render(new RasterContext(raster));
        logger.info("Chart for {}:\n{}", curveModel, raster.toString());
    }

}
