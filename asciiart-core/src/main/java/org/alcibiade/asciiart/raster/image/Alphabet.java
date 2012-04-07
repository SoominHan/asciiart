package org.alcibiade.asciiart.raster.image;

import java.awt.Color;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Alphabet {

    private static final String AVAILABLE_CHARACTERS = " "
            + "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "~!@#$%^&*()_+`-=[]{}\\|;:'<>,.?/";
    private Map<BufferedImage, Character> letters;

    public Alphabet(Dimension2D letterSize) {
        this(letterSize, AVAILABLE_CHARACTERS);
    }

    public Alphabet(Dimension2D letterSize, String availableCharacters) {
        letters = new HashMap<BufferedImage, Character>();
        for (int i = 0; i < availableCharacters.length(); i++) {
            Character c = availableCharacters.charAt(i);
            BufferedImage letterImage = new LetterImage(letterSize, c);
            letters.put(letterImage, c);
        }
    }

    public Character match(BufferedImage image, Rectangle2D zone) {
        long bestMatchFactor = Long.MAX_VALUE;
        Character bestCharacter = null;

        for (Map.Entry<BufferedImage, Character> entry : letters.entrySet()) {
            long matchFactor = matchFactor(image, zone, entry.getKey());

            if (matchFactor < bestMatchFactor) {
                bestCharacter = entry.getValue();
                bestMatchFactor = matchFactor;
            }
        }

        assert bestCharacter != null;

        return bestCharacter;
    }

    private long matchFactor(BufferedImage image1, Rectangle2D zone1,
            BufferedImage image2) {

        long difference = 0;

        for (int y = 0; y < (int) zone1.getHeight() && y < image2.getHeight(); y++) {
            for (int x = 0; x < (int) zone1.getWidth() && x < image2.getWidth(); x++) {
                int rgb1 = image1.getRGB((int) zone1.getMinX() + x, (int) zone1.getMinY() + y);
                int rgb2 = image2.getRGB(x, y);

                int distance = rgbDistance(rgb1, rgb2);
                difference += distance;
            }
        }

        return Math.abs(difference);
    }

    private int rgbDistance(int rgb1, int rgb2) {
        Color c1 = new Color(rgb1);
        Color c2 = new Color(rgb2);
        int brightness1 = (c1.getRed() + c1.getGreen() + c1.getBlue()) / 3;
        int brightness2 = (c2.getRed() + c2.getGreen() + c2.getBlue()) / 3;
        int distance = brightness1 - brightness2;
        return distance;
    }
}
