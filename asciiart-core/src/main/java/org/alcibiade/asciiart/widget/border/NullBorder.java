package org.alcibiade.asciiart.widget.border;

public class NullBorder extends AbstractBorder {

    public NullBorder() {
        this(0, 0, 0, 0, 0, 0);
    }

    public NullBorder(int marginTop, int maringLeft, int marginRight,
            int marginBottom, int overlapH, int overlapV) {
        super(marginTop, maringLeft, marginRight, marginBottom, overlapH,
                overlapV);
    }
}
