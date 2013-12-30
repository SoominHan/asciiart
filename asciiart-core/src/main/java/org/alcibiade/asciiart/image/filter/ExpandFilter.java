package org.alcibiade.asciiart.image.filter;

import java.awt.image.BufferedImage;

/**
 * Detect edges in image.
 *
 * @author Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */
public class ExpandFilter implements ImageFilter {

    @Override
    public BufferedImage filter(BufferedImage image) {
        int targetWidth = image.getWidth();
        int targetHeight = image.getHeight();

        BufferedImage edgesImage = new BufferedImage(targetWidth, targetHeight,
                BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int resultLevel = 255;

                for (int dy = -1; dy < 2; dy++) {
                    for (int dx = -1; dx < 2; dx++) {
                        int ax = x + dx;
                        int ay = y + dy;

                        if (ax >= 0 && ax < image.getWidth() && ay >= 0 && ay < image.getHeight()) {
                            int value = image.getRGB(ax, ay) & 0xFF;
                            if (value < resultLevel) {
                                resultLevel = value;
                            }
                        }
                    }
                }

                edgesImage.setRGB(x, y, resultLevel << 16 | resultLevel << 8 | resultLevel);
            }
        }

        return edgesImage;
    }

}
