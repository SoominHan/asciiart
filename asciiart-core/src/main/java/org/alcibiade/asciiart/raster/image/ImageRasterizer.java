package org.alcibiade.asciiart.raster.image;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import org.alcibiade.asciiart.coord.TextBox;
import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.coord.TextCoord;
import org.alcibiade.asciiart.raster.RasterContext;

public class ImageRasterizer {

    public static void rasterize(BufferedImage image, RasterContext rc,
            TextBoxSize size) {
        Dimension2D letterSize = new Dimension(image.getWidth(null)
                / size.getX(), image.getHeight(null) / size.getY());

        Alphabet alphabet = new Alphabet(letterSize);

        for (TextCoord coord : new TextBox(size)) {
            double baseX = coord.getX() * letterSize.getWidth();
            double baseY = coord.getY() * letterSize.getHeight();
            Rectangle2D zone = new Rectangle2D.Double(baseX, baseY, baseX
                    + letterSize.getWidth(), baseY + letterSize.getHeight());
            Character c = alphabet.match(image, zone);
            rc.drawCharacter(coord, c);
        }
    }
}
