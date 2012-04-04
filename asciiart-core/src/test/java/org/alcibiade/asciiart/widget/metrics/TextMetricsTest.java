package org.alcibiade.asciiart.widget.metrics;

import org.junit.Assert;
import org.junit.Test;

public class TextMetricsTest {

    @Test
    public void testZero() {
        Assert.assertEquals(new TextMetrics(0, 0), new TextMetrics());
        Assert.assertEquals(new TextMetrics(0, 0), new TextMetrics(""));
    }

    @Test
    public void testText() {
        Assert.assertEquals(new TextMetrics(4, 0), new TextMetrics("abcd"));
        Assert.assertEquals(new TextMetrics(11, 0), new TextMetrics("command.com"));
        Assert.assertEquals(new TextMetrics(5, 0), new TextMetrics("1.2.3"));
    }

    @Test
    public void testIsNumeric() {
        Assert.assertTrue(TextMetrics.isNumeric("2"));
        Assert.assertTrue(TextMetrics.isNumeric("2."));
        Assert.assertTrue(TextMetrics.isNumeric("2.2"));
        Assert.assertFalse(TextMetrics.isNumeric("2.2a"));
        Assert.assertFalse(TextMetrics.isNumeric("a"));
        Assert.assertFalse(TextMetrics.isNumeric(" 2"));
        Assert.assertFalse(TextMetrics.isNumeric(" 2."));
        Assert.assertFalse(TextMetrics.isNumeric(" 2.2"));
        Assert.assertFalse(TextMetrics.isNumeric("2 "));
        Assert.assertFalse(TextMetrics.isNumeric("2. "));
        Assert.assertFalse(TextMetrics.isNumeric("2.2 "));
    }
}
