package org.alcibiade.asciiart.raster.image;

import java.awt.Dimension;
import java.io.IOException;
import org.junit.Test;

public class LetterImageTest {

    @Test
    public void testNew() throws IOException {
        Dimension size = new Dimension(64, 128);
        new LetterImage(size, 'X');
    }
}
