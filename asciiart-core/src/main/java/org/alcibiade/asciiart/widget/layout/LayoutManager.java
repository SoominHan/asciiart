package org.alcibiade.asciiart.widget.layout;

import java.util.List;
import org.alcibiade.asciiart.coord.TextBox;
import org.alcibiade.asciiart.widget.TextWidget;
import org.alcibiade.asciiart.widget.border.WidgetBorder;

public interface LayoutManager {

    List<TextBox> layout(List<TextWidget> widgets, WidgetBorder border);
}
