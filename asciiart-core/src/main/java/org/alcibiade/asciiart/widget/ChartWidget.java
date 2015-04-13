package org.alcibiade.asciiart.widget;

import java.util.ArrayList;
import java.util.List;
import org.alcibiade.asciiart.coord.CoordMapper;
import org.alcibiade.asciiart.coord.CoordProjection;
import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.coord.TextCoord;
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
    private double minX, maxX;

    public ChartWidget(TextBoxSize size, CurveModel curveModel, double minX, double maxX) {
        this.size = size;
        this.curveModel = curveModel;
        this.minX = minX;
        this.maxX = maxX;
    }

    @Override
    public TextBoxSize getSize() {
        return size;
    }

    @Override
    public void render(RasterContext rc) {
        CoordProjection projectGridToCurve = new CoordProjection(0, size.getX(), minX, maxX);

        // Calculate Y values
        List<Double> values = new ArrayList<Double>();

        for (int gridX = 0; gridX < size.getX(); gridX++) {
            double curveX = projectGridToCurve.project(gridX);
            Double value = curveModel.getValue(curveX);
            values.add(value);
        }

        // Compute statistics
        double minY = Double.POSITIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;

        for (Double value : values) {
            if (value != null && value != Double.NaN) {
                if (value > maxY) {
                    maxY = value;
                }

                if (value < minY) {
                    minY = value;
                }
            }
        }

        double margin = (maxY - minY) * .1;

        minY -= margin;
        maxY += margin;

        // Proceed with actual rendering
        CoordMapper coordMapper = new CoordMapper(size, minX, maxX, minY, maxY);

        for (int gridX = 0; gridX < size.getX(); gridX++) {
            double curveX = projectGridToCurve.project(gridX);
            Double value = values.get(gridX);

            // The curve may hold no valid value at that position.
            if (value != null && value != Double.NaN) {
                TextCoord coord = coordMapper.computeTextCoord(curveX, value);
                // The curve should not have an out of bound value based on our current algorithm
                assert coord != null;
                rc.drawCharacter(coord, '*');
            }
        }

    }

}
