package org.alcibiade.asciiart.image.filter;

import java.awt.image.BufferedImage;

/**
 * Equalize image to extend lightness range.
 *
 * @author Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */
public class EqualizeFilter implements ImageFilter {

    @Override
    public BufferedImage filter(BufferedImage image) {
        long total = 0;
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int level = image.getRGB(x, y) & 0xff;
                total += level;
            }
        }

        int average = (int) (total / (width * height));
        double ratio = average / 128.;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int level = image.getRGB(x, y) & 0xff;
                level = (int) (255 * Math.pow(level / 255., ratio));
                image.setRGB(x, y, level | (level << 8) | (level << 16));
            }
        }

        return image;
    }
}
