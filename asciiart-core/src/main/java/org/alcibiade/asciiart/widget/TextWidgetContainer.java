package org.alcibiade.asciiart.widget;

import java.util.ArrayList;
import java.util.List;

public abstract class TextWidgetContainer extends TextWidget {

    private List<TextWidget> widgets;

    public TextWidgetContainer() {
        widgets = new ArrayList<TextWidget>();
    }

    public void add(TextWidget widget) {
        widgets.add(widget);
    }

    public void remove(TextWidget widget) {
        widgets.remove(widget);
    }

    public void removeAll() {
        widgets.clear();
    }

    public List<TextWidget> getWidgets() {
        return widgets;
    }
}
