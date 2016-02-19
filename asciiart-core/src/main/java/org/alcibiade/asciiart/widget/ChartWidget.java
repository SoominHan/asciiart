package org.alcibiade.asciiart.widget;

import org.alcibiade.asciiart.coord.CoordMapper;
import org.alcibiade.asciiart.coord.CoordProjection;
import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.coord.TextCoord;
import org.alcibiade.asciiart.raster.RasterContext;
import org.alcibiade.asciiart.widget.model.CurveModel;

import java.util.ArrayList;
import java.util.List;

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
        CoordProjection projectGridXToCurve = new CoordProjection(0, size.getX(), minX, maxX);

        // Calculate Y values
        List<Double> values = new ArrayList<Double>();

        for (int gridX = 0; gridX < size.getX(); gridX++) {
            // Compute 2 samples for every character cell
            double curveX0 = projectGridXToCurve.project(gridX + 0.25);
            double curveX1 = projectGridXToCurve.project(gridX + 0.75);
            values.add(curveModel.getValue(curveX0));
            values.add(curveModel.getValue(curveX1));
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

        CoordProjection projectGridYToCurve = new CoordProjection(0, size.getY(), minY, maxY);

        double margin = (maxY - minY) * .1;

        minY -= margin;
        maxY += margin;

        // Proceed with actual rendering
        // Note that the coordinate mapping will swap Y coordinates
        CoordMapper coordMapper = new CoordMapper(size, minX, maxX, maxY, minY);

        // Render X axis
        TextCoord originCoord = coordMapper.computeTextCoord(0, 0);

        if (originCoord != null) {
            for (int x = 0; x < size.getX(); x++) {
                rc.drawCharacter(new TextCoord(x, originCoord.getY()), '.');
            }
            for (int y = 0; y < size.getY(); y++) {
                rc.drawCharacter(new TextCoord(originCoord.getX(), y), '|');
            }

            rc.drawCharacter(originCoord, '+');
            rc.drawCharacter(new TextCoord(originCoord.getX(), 0), '^');
            rc.drawCharacter(new TextCoord(size.getX() - 1, originCoord.getY()), '>');
        }

        Integer lastY = null;

        // Render the curve itself
        for (int gridX = 0; gridX < size.getX(); gridX++) {
            double curveX = projectGridXToCurve.project(gridX);
            Double value0 = values.get(2 * gridX);
            Double value1 = values.get(2 * gridX + 1);

            // The curve may hold no valid value at that position.

            Character curveChar;
            TextCoord coord;

            if (value0 == null || value0 == Double.NaN) {
                coord = coordMapper.computeTextCoord(curveX, value1);

                if (value1 == null || value1 == Double.NaN) {
                    curveChar = null;
                } else {
                    curveChar = '-';
                }
            } else {
                coord = coordMapper.computeTextCoord(curveX, value0);

                if (value1 == null || value1 == Double.NaN) {
                    curveChar = '-';
                    coord = coordMapper.computeTextCoord(curveX, value0);
                } else {
                    // Two values are defined
                    double curveStep = Math.abs(projectGridYToCurve.project(0) - projectGridYToCurve.project(1));

                    if (Math.abs(value0 - value1) < curveStep / 3) {
                        curveChar = '-';
                    } else if (value0 < value1) {
                        curveChar = '/';
                    } else {
                        curveChar = '\\';
                    }
                }
            }

            if (curveChar == null) {
                lastY = null;
            } else {
                // The curve should not have an out of bound value based on our current algorithm
                assert coord != null;

                if (lastY != null) {
                    if (coord.getY() > lastY + 1) {
                        for (int y = lastY + 1; y < coord.getY(); y++) {
                            rc.drawCharacter(new TextCoord(coord.getX(), y), '|');
                        }
                    } else if (coord.getY() < lastY - 1) {
                        for (int y = lastY - 1; y > coord.getY(); y--) {
                            rc.drawCharacter(new TextCoord(coord.getX(), y), '|');
                        }
                    }
                }

                rc.drawCharacter(coord, curveChar);

                lastY = coord.getY();
            }
        }

    }

}
