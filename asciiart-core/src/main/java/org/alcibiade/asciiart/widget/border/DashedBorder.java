package org.alcibiade.asciiart.widget.border;

import org.alcibiade.asciiart.coord.TextBox;
import org.alcibiade.asciiart.coord.TextCoord;
import org.alcibiade.asciiart.raster.RasterContext;

public class DashedBorder extends AbstractBorder {

    public DashedBorder() {
        super(1, 2, 2, 1, 1, 1);
    }

    @Override
    public void render(RasterContext rc, TextBox box) {
        box = extendMargins(box);
        TextCoord lowCoord = box.getLowCoord();
        TextCoord highCoord = box.getHighCoord();

        rc.drawString(new TextCoord(lowCoord.getX(), lowCoord.getY()), "+");
        rc.drawString(
                new TextCoord(lowCoord.getX(), highCoord.getY() - 1),
                "+");
        rc.drawString(
                new TextCoord(highCoord.getX() - 1, lowCoord.getY()),
                "+");
        rc.drawString(
                new TextCoord(highCoord.getX() - 1, highCoord.getY() - 1), "+");

        for (int y = lowCoord.getY() + 1; y < highCoord.getY() - 1; y++) {
            rc.drawString(new TextCoord(lowCoord.getX(), y), "|");
            rc.drawString(new TextCoord(highCoord.getX() - 1, y), "|");
        }

        for (int x = lowCoord.getX() + 1; x < highCoord.getX() - 1; x++) {
            rc.drawString(new TextCoord(x, lowCoord.getY()), "-");
            rc.drawString(new TextCoord(x, highCoord.getY() - 1), "-");
        }
    }

    private TextBox extendMargins(TextBox box) {
        TextCoord lowCoord = box.getLowCoord();
        TextCoord highCoord = box.getHighCoord();

        box = new TextBox(new TextCoord(lowCoord.getX() - getMarginLeft(),
                lowCoord.getY() - getMarginTop()), new TextCoord(highCoord.getX()
                + getMarginRight(), highCoord.getY() + getMarginBottom()));

        return box;
    }
}
