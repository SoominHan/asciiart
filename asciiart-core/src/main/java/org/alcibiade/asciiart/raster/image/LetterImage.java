package org.alcibiade.asciiart.raster.image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class LetterImage extends BufferedImage {

    private static final int LEVEL_LIGHT = 255;
    private static final int LEVEL_DARK = 0;
    private static final Color COLOR_LIGHT = new Color(LEVEL_LIGHT,
            LEVEL_LIGHT, LEVEL_LIGHT);
    private static final Color COLOR_DARK = new Color(LEVEL_DARK, LEVEL_DARK,
            LEVEL_DARK);
    private static final double OVERLAPPING_FACTOR = 1.3;
    private static final int FONT_STYLE = Font.BOLD;
    private static final String FONT_FAMILY = "Monospaced";
    private static final double HEIGHTBASE = 0.80;

    public LetterImage(Dimension2D size, Character character) {
        this(size, character, true);
    }

    public LetterImage(Dimension2D size, Character character,
            boolean darkOnLight) {
        super((int) size.getWidth(), (int) size.getHeight(),
                BufferedImage.TYPE_BYTE_GRAY);
        assert size.getWidth() > 4;
        assert size.getHeight() > 4;

        Graphics2D g2d = createGraphics();

        if (darkOnLight) {
            g2d.setColor(COLOR_DARK);
            g2d.setBackground(COLOR_LIGHT);
        } else {
            g2d.setColor(COLOR_LIGHT);
            g2d.setBackground(COLOR_DARK);
        }

        g2d.clearRect(0, 0, getWidth(), getHeight());

        g2d.setFont(computeBestFont(g2d, size, character));
        g2d.drawString("" + character, 0, (int) (HEIGHTBASE * size.getHeight()));
        g2d.dispose();
    }

    private Font computeBestFont(Graphics2D g2d, Dimension2D size,
            Character character) {
        int fontSize = (int) (Math.min(size.getWidth(), size.getHeight()) * 0.9);

        size = new Dimension((int) (size.getWidth() * OVERLAPPING_FACTOR),
                (int) (size.getHeight() * OVERLAPPING_FACTOR));

        Rectangle2D outerBounds = new Rectangle2D.Double(0, -size.getHeight()
                * HEIGHTBASE, size.getWidth(), size.getHeight());
        Rectangle2D characterBounds;

        do {
            Font font = new Font(FONT_FAMILY, FONT_STYLE, fontSize);
            FontMetrics metrics = g2d.getFontMetrics(font);
            characterBounds = metrics.getStringBounds("" + character, g2d);
            fontSize += 2;
        } while (outerBounds.contains(characterBounds));

        assert fontSize > 4;

        return new Font(FONT_FAMILY, FONT_STYLE, fontSize - 4);
    }
}
