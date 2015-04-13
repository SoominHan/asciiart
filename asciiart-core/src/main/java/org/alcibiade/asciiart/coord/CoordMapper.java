package org.alcibiade.asciiart.coord;

/**
 * Map between coordinate spaces.
 *
 * @author Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */
public class CoordMapper {

    private TextBoxSize textBoxSize;
    private double minX, maxX, minY, maxY;
    private CoordProjection projectTextToDoubleX;
    private CoordProjection projectTextToDoubleY;
    private CoordProjection projectDoubleToTextX;
    private CoordProjection projectDoubleToTextY;

    public CoordMapper(TextBoxSize textBoxSize, double minX, double maxX, double minY, double maxY) {
        this.textBoxSize = textBoxSize;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        projectTextToDoubleX = new CoordProjection(0, textBoxSize.getX(), minX, maxX);
        projectTextToDoubleY = new CoordProjection(0, textBoxSize.getY(), minY, maxY);
        projectDoubleToTextX = new CoordProjection(minX, maxX, 0, textBoxSize.getX());
        projectDoubleToTextY = new CoordProjection(minY, maxY, 0, textBoxSize.getY());
    }

    public TextCoord computeTextCoord(double x, double y) {
        TextCoord result = null;

        int textX = (int) projectDoubleToTextX.project(x);
        int textY = (int) projectDoubleToTextY.project(y);

        TextCoord textCoord = new TextCoord(textX, textY);
        if (new TextBox(textBoxSize).isInside(textCoord)) {
            result = textCoord;
        }

        return result;
    }

}
