package org.alcibiade.asciiart.image.filter;

import java.awt.image.BufferedImage;

/**
 * Filter an image to pre-process contents.
 *
 * @author Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */
public interface ImageFilter {

    BufferedImage filter(BufferedImage image);
}
