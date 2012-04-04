package org.alcibiade.asciiart.widget.metrics;

import java.util.regex.Pattern;

public class TextMetrics {

    private int width;
    private int precision;

    public TextMetrics() {
        this.width = 0;
        this.precision = 0;
    }

    public TextMetrics(int width, int precision) {
        this.width = width;
        this.precision = precision;
    }

    public TextMetrics(String text) {
        int dotIndex = text.indexOf('.');

        if (!isNumeric(text) || dotIndex == -1) {
            width = text.length();
            precision = 0;
        } else {
            width = dotIndex;
            precision = text.length() - dotIndex - 1;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getPrecision() {
        return precision;
    }

    public TextMetrics combine(TextMetrics metrics) {
        return new TextMetrics(Math.max(width, metrics.width), Math.max(
                precision, metrics.precision));
    }

    protected static boolean isNumeric(String text) {
        return Pattern.matches("[0-9]*\\.?[0-9]*", text);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        if (obj instanceof TextMetrics) {
            TextMetrics m = (TextMetrics) obj;
            result = width == m.width && precision == m.precision;
        }

        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return 17 * width + precision;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("TextMetrics{%d,%d}", width, precision);
    }
}
