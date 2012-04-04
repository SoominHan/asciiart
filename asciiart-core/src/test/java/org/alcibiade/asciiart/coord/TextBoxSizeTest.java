package org.alcibiade.asciiart.coord;

import org.junit.Assert;
import org.junit.Test;

public class TextBoxSizeTest {

    @Test
    public void testNew() {
        new TextBoxSize(0, 0);

        new TextBoxSize(3, 2);

        try {
            new TextBoxSize(-1, 2);
            Assert.fail();
        } catch (TextCoordException e) {
            // Do nothing
        }
    }
}
