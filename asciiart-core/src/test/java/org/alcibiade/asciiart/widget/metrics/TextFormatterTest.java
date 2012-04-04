package org.alcibiade.asciiart.widget.metrics;

import org.junit.Assert;
import org.junit.Test;

public class TextFormatterTest {

    @Test
    public void testFormatText() {
        TextFormatter formatter = new TextFormatter();
        formatter.considerText("abc");
        formatter.considerText("1425");
        formatter.considerText("1.2.");
        formatter.considerText(".a");
        formatter.considerText("a.b");
        formatter.considerText("");
        formatter.considerText("%");
        Assert.assertEquals(4, formatter.getMetrics().getWidth());
        Assert.assertEquals(0, formatter.getMetrics().getPrecision());
        Assert.assertEquals("ab  ", formatter.format("ab"));
        Assert.assertEquals("   4", formatter.format("4"));
        Assert.assertEquals("   4.3", formatter.format("4.3"));
    }
}
