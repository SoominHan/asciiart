package org.alcibiade.asciiart.widget.layout;

import java.util.ArrayList;
import java.util.List;
import org.alcibiade.asciiart.coord.TextBox;
import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.coord.TextCoord;
import org.alcibiade.asciiart.widget.TextWidget;
import org.alcibiade.asciiart.widget.border.WidgetBorder;

public class BoxLayout extends AbstractLayout {

    public static final boolean Y = true;
    public static final boolean X = false;
    private boolean orientation;

    public BoxLayout() {
        this(Y);
    }

    public BoxLayout(boolean orientation) {
        this.orientation = orientation;
    }

    @Override
    public List<TextBox> layout(List<TextWidget> widgets, WidgetBorder border) {
        List<TextBox> result = new ArrayList<TextBox>();

        TextCoord coord = TextCoord.ORIGIN;

        for (TextWidget widget : widgets) {
            TextBoxSize widgetSize = widget.getSize();
            TextCoord widgetOffset = coord.add(new TextCoord(border.getMarginLeft(), border.getMarginTop()));

            result.add(new TextBox(widgetOffset, widgetOffset.add(widgetSize)));

            if (orientation == X) {
                coord = coord.add(new TextCoord(widgetSize.getX()
                        + border.getMarginLeft() + border.getMarginRight()
                        - border.getOverlapH(), 0));
            } else {
                coord = coord.add(new TextCoord(0, widgetSize.getY()
                        + border.getMarginTop() + border.getMarginBottom()
                        - border.getOverlapV()));
            }
        }

        return result;
    }
}
