package org.alcibiade.asciiart.image.rasterize;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Basic Dash rasterizer.
 *
 * @author Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */
public class ShapeRasterizer extends AbstractLinearRasterizer {

    private static final int SHAPE_RESOLUTION_X = 3;
    private static final int SHAPE_RESOLUTION_Y = 3;
    private char[] shapes;
    private double delta = 0;

    public ShapeRasterizer() {
        String resourceBundle = String.format(
                "shapes_%dx%d.properties", SHAPE_RESOLUTION_X, SHAPE_RESOLUTION_Y);

        try {
            Properties shapeProperties = new Properties();
            InputStream in = getClass().getResourceAsStream(resourceBundle);
            shapeProperties.load(in);
            in.close();

            int shapeItems = 1 << (SHAPE_RESOLUTION_X * SHAPE_RESOLUTION_Y);

            shapes = new char[shapeItems];

            for (int shapeIndex = 0; shapeIndex < shapeItems; shapeIndex++) {
                char shapeChar = '?';
                String key = Integer.toString(shapeIndex);
                String value = shapeProperties.getProperty(key);

                if (value != null) {
                    if (value.isEmpty()) {
                        shapeChar = ' ';
                    } else {
                        shapeChar = value.charAt(0);
                    }
                }

                shapes[shapeIndex] = shapeChar;
            }

        } catch (IOException ex) {
            throw new IllegalStateException("Failed to load resource bundle " + resourceBundle, ex);
        }

    }

    @Override
    protected char rasterizeSection(BufferedImage image, Rectangle sectionArea) {
        ImageSectionLightnessExtractor lightnessExtractor = getLightnessExtractor();

        int subSectionW = (int) (sectionArea.getWidth() / 3);
        int subSectionH = (int) (sectionArea.getHeight() / 3);
        double[] lightMatrix = new double[SHAPE_RESOLUTION_X * SHAPE_RESOLUTION_Y];

        for (int y = 0; y < SHAPE_RESOLUTION_Y; y++) {
            for (int x = 0; x < SHAPE_RESOLUTION_X; x++) {
                Rectangle area = new Rectangle(
                        (int) (sectionArea.getMinX() + x * subSectionW),
                        (int) (sectionArea.getMinY() + y * subSectionH),
                        subSectionW, subSectionH);

                double lightness = lightnessExtractor.getLightness(image, area);
                lightMatrix[SHAPE_RESOLUTION_X * y + x] = lightness;
            }
        }

        int shapeOffset = computeOffset(lightMatrix);
        char result = shapes[shapeOffset];
        return result;
    }

    private int computeOffset(double[] lightMatrix) {
        int offset = 0;

        for (int i = 0; i < lightMatrix.length; i++) {
            double initialValue = lightMatrix[i];
            double correctedValue = initialValue + delta;
            double selectedValue = (correctedValue > 0.5) ? 1 : 0;

            delta = correctedValue - selectedValue;

            if (selectedValue > 0.5) {
                offset += 1 << i;
            }
        }

        return offset;
    }
}
