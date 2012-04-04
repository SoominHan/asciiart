package org.alcibiade.asciiart.widget.model;

public interface TableModel extends TextWidgetModel, Iterable<String> {

    int getWidth();

    int getHeight();

    String getCellContent(int x, int y);

    String getColumnTitle(int x);
}
