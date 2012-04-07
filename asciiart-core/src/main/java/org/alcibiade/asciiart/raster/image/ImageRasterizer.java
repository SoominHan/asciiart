package org.alcibiade.asciiart.raster.image;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.alcibiade.asciiart.coord.TextBox;
import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.coord.TextCoord;
import org.alcibiade.asciiart.raster.RasterContext;

public class ImageRasterizer {

    public static void rasterize(BufferedImage image, RasterContext rc,
            TextBoxSize size) {
        BufferedImage bwcopy = preprocessImage(image, size);
        int picHeight = bwcopy.getHeight();
        int picWidth = bwcopy.getWidth();

        Dimension2D letterSize = new Dimension(picWidth
                / size.getX(), picHeight / size.getY());

        Alphabet alphabet = new Alphabet(letterSize);

        for (TextCoord coord : new TextBox(size)) {
            double baseX = coord.getX() * picWidth / size.getX();
            double baseY = coord.getY() * picHeight / size.getY();

            Rectangle2D zone = new Rectangle2D.Double(baseX, baseY,
                    letterSize.getWidth(), letterSize.getHeight());

            Character c = alphabet.match(bwcopy, zone);
            rc.drawCharacter(coord, c);
        }
    }

    private static BufferedImage preprocessImage(BufferedImage image, TextBoxSize size) {
        int targetWidth = Math.max(image.getWidth(), size.getX() * 12);
        int targetHeight = Math.max(image.getHeight(), size.getY() * 20);

        BufferedImage bwcopy = new BufferedImage(targetWidth, targetHeight,
                BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D g2 = bwcopy.createGraphics();
        g2.drawImage(image, 0, 0, targetWidth, targetHeight, 0, 0, image.getWidth(),
                image.getHeight(), null);

        return bwcopy;
    }
}
