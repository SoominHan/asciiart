package org.alcibiade.asciiart.widget;

import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.raster.RasterContext;
import org.alcibiade.asciiart.widget.model.CurveModel;

/**
 * Display various charts.
 *
 * @author Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */
public class ChartWidget extends TextWidget {

    private TextBoxSize size;
    private CurveModel curveModel;

    public ChartWidget(TextBoxSize size, CurveModel curveModel, double minX, double maxX) {
        this.size = size;
        this.curveModel = curveModel;
    }

    @Override
    public TextBoxSize getSize() {
        return size;
    }

    @Override
    public void render(RasterContext rc) {
        // TODO: Implement chart widget
    }

}
