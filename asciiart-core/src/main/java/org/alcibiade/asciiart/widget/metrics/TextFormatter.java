package org.alcibiade.asciiart.widget.metrics;

public class TextFormatter {

    private TextMetrics metrics = new TextMetrics();

    public void considerText(String text) {
        TextMetrics localMetrics = new TextMetrics(text);
        metrics = metrics.combine(localMetrics);
    }

    public String format(String text) {
        TextMetrics localMetrics = new TextMetrics(text);
        boolean numericText = TextMetrics.isNumeric(text);

        int marginL = metrics.getWidth() - localMetrics.getWidth();

        while (marginL > 0) {
            if (numericText) {
                text = " " + text;
            } else {
                text = text + " ";
            }

            marginL--;
        }

        int marginR = metrics.getPrecision() - localMetrics.getPrecision();

        while (marginR > 0) {
            text = text + " ";
            marginR--;
        }

        return text;
    }

    public TextMetrics getMetrics() {
        return metrics;
    }
}
