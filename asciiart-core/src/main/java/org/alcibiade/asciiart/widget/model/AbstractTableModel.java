package org.alcibiade.asciiart.widget.model;

import java.util.Iterator;

public abstract class AbstractTableModel extends AbstractWidgetModel implements
        TableModel {

    @Override
    public Iterator<String> iterator() {
        return new TableModelIterator(this);
    }

    @Override
    public String getColumnTitle(int x) {
        return null;
    }
}