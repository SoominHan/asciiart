package org.alcibiade.asciiart.image.filter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Convert image to black and white.
 *
 * @author Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */
public class BlackAndWhiteFilter implements ImageFilter {

    @Override
    public BufferedImage filter(BufferedImage image) {
        int targetWidth = image.getWidth();
        int targetHeight = image.getHeight();
        
        BufferedImage bwcopy = new BufferedImage(targetWidth, targetHeight,
                BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D g2 = bwcopy.createGraphics();

        g2.drawImage(image, 0, 0, targetWidth, targetHeight, 0, 0, image.getWidth(),
                image.getHeight(), null);

        return bwcopy;
    }
}
