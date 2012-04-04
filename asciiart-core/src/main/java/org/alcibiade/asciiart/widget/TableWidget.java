package org.alcibiade.asciiart.widget;

import java.util.ArrayList;
import java.util.List;
import org.alcibiade.asciiart.widget.border.DashedBorder;
import org.alcibiade.asciiart.widget.border.NullBorder;
import org.alcibiade.asciiart.widget.layout.BoxLayout;
import org.alcibiade.asciiart.widget.layout.GridLayout;
import org.alcibiade.asciiart.widget.metrics.TextFormatter;
import org.alcibiade.asciiart.widget.model.ModelUpdateListener;
import org.alcibiade.asciiart.widget.model.TableModel;

public class TableWidget extends TextPanel implements ModelUpdateListener {

    private TableModel tableModel;

    public TableWidget(TableModel tableModel) {
        this(tableModel, true);
    }

    public TableWidget(TableModel tableModel, boolean hasBorder) {
        super(new GridLayout());

        if (hasBorder) {
            setBorder(new DashedBorder());
        } else {
            setBorder(new NullBorder(0, 0, 1, 0, 0, 0));
        }

        this.tableModel = tableModel;
        this.tableModel.addModelUpdateListener(this);
        repack();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.alcibiade.asciiart.widget.model.ModelUpdateListener#modelUpdated(
     * java.lang.Object)
     */
    @Override
    public void modelUpdated(Object source) {
        repack();
    }

    /**
     *
     */
    private void repack() {
        int modelWidth = tableModel.getWidth();

        // Create formatters

        List<TextFormatter> formatters = new ArrayList<TextFormatter>();

        for (int x = 0; x < modelWidth; x++) {
            TextFormatter formatter = new TextFormatter();

            for (int y = 0; y < tableModel.getHeight(); y++) {
                formatter.considerText(tableModel.getCellContent(x, y));
            }

            formatters.add(formatter);
        }

        // Init layout

        removeAll();
        setLayout(new GridLayout(modelWidth));

        // Create titles

        boolean hasTitleBar = false;
        List<LabelWidget> titles = new ArrayList<LabelWidget>();
        for (int x = 0; x < modelWidth; x++) {
            String title = tableModel.getColumnTitle(x);
            if (title == null) {
                title = "";
            } else {
                hasTitleBar = true;
            }

            titles.add(new LabelWidget(title));
        }

        if (hasTitleBar) {
            for (LabelWidget title : titles) {
                add(title);
            }
        }

        // Create inner panels

        List<TextPanel> innerPanels = new ArrayList<TextPanel>();
        for (int x = 0; x < modelWidth; x++) {
            TextPanel panel = new TextPanel(new BoxLayout(BoxLayout.Y));
            innerPanels.add(panel);
            add(panel);
        }

        int x = 0;
        for (String content : tableModel) {
            String formattedContent = formatters.get(x).format(content);
            LabelWidget label = new LabelWidget(formattedContent);

            innerPanels.get(x).add(label);
            x = (x + 1) % modelWidth;
        }
    }
}
