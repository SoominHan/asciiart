package org.alcibiade.asciiart.widget;

import java.util.List;
import org.alcibiade.asciiart.coord.TextBox;
import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.coord.TextCoord;
import org.alcibiade.asciiart.raster.RasterContext;
import org.alcibiade.asciiart.widget.border.NullBorder;
import org.alcibiade.asciiart.widget.border.WidgetBorder;
import org.alcibiade.asciiart.widget.layout.BoxLayout;
import org.alcibiade.asciiart.widget.layout.LayoutManager;

public class TextPanel extends TextWidgetContainer {

    private LayoutManager layout;
    private WidgetBorder border;

    public TextPanel() {
        this(new BoxLayout());
    }

    public TextPanel(LayoutManager layout) {
        this(layout, new NullBorder());
    }

    public TextPanel(LayoutManager layout, WidgetBorder border) {
        this.layout = layout;
        this.border = border;
    }

    /**
     * Widget borders
     */
    public void setBorder(WidgetBorder border) {
        this.border = border;
    }

    public WidgetBorder getBorder() {
        return border;
    }

    /**
     * Layout manager
     */
    public LayoutManager getLayout() {
        return layout;
    }

    public void setLayout(LayoutManager layout) {
        this.layout = layout;
    }

    @Override
    public void render(RasterContext rc) {
        List<TextWidget> widgets = getWidgets();
        List<TextBox> boxes = layout.layout(widgets, border);

        for (int i = 0; i < widgets.size(); i++) {
            TextWidget widget = widgets.get(i);
            TextBox box = boxes.get(i);
            rc.translate(box.getLowCoord());
            widget.render(rc);
            rc.translate(box.getLowCoord().invert());

            border.render(rc, box);
        }
    }

    @Override
    public TextBoxSize getSize() {
        TextBox bounds = new TextBox(TextCoord.ORIGIN);

        for (TextBox box : layout.layout(getWidgets(), border)) {
            bounds = new TextBox(bounds, box);
        }

        bounds = new TextBox(bounds.getLowCoord(), bounds.getHighCoord().add(
                new TextCoord(border.getMarginRight(), border.getMarginBottom())));

        return bounds.getSize();
    }
}
