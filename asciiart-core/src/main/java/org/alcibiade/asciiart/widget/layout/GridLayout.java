package org.alcibiade.asciiart.widget.layout;

import java.util.ArrayList;
import java.util.List;
import org.alcibiade.asciiart.coord.TextBox;
import org.alcibiade.asciiart.widget.TextWidget;
import org.alcibiade.asciiart.widget.border.WidgetBorder;

public class GridLayout extends AbstractLayout {

    private int columns;

    public GridLayout() {
        this(2);
    }

    public GridLayout(int columns) {
        this.columns = columns;
    }

    @Override
    public List<TextBox> layout(List<TextWidget> widgets, WidgetBorder border) {
        List<TextBox> result = new ArrayList<TextBox>();

        List<Integer> widths = computeColumnWidths(widgets);
        List<Integer> heights = computeColumnHeights(widgets);

        for (int i = 0; i < widgets.size(); i++) {
            int x = i % columns;
            int y = i / columns;
            int offsX = 0;
            int offsY = 0;

            for (int j = 0; j < x; j++) {
                offsX += widths.get(j);
                offsX += border.getMarginLeft() + border.getMarginRight()
                        - border.getOverlapH();
            }

            for (int j = 0; j < y; j++) {
                offsY += heights.get(j);
                offsY += border.getMarginTop() + border.getMarginBottom()
                        - border.getOverlapV();
            }

            int baseX = offsX + border.getMarginLeft();
            int baseY = offsY + border.getMarginTop();
            result.add(new TextBox(baseX, baseY, baseX + widths.get(x), baseY
                    + heights.get(y)));
        }

        return result;
    }

    private List<Integer> computeColumnWidths(List<TextWidget> widgets) {
        List<Integer> widths = new ArrayList<Integer>();

        for (int i = 0; i < columns; i++) {
            widths.add(Integer.MIN_VALUE);
        }

        for (int i = 0; i < widgets.size(); i++) {
            int colIndex = i % columns;
            int colWidth = widths.get(colIndex);
            int widgetWidth = widgets.get(i).getSize().getX();
            widths.set(colIndex, Math.max(colWidth, widgetWidth));
        }

        return widths;
    }

    private List<Integer> computeColumnHeights(List<TextWidget> widgets) {
        List<Integer> heights = new ArrayList<Integer>();

        for (int i = 0; i < widgets.size(); i++) {
            int rowIndex = i / columns;

            if (heights.size() < 1 + rowIndex) {
                heights.add(Integer.MIN_VALUE);
            }

            int rowHeight = heights.get(rowIndex);
            int widgetheight = widgets.get(i).getSize().getY();
            heights.set(rowIndex, Math.max(rowHeight, widgetheight));
        }

        return heights;
    }
}
