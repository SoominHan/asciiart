package org.alcibiade.asciiart.widget.model;

import java.util.Collection;
import java.util.Iterator;

public class TableModelCollectionAdapter extends AbstractTableModel {

    private String title;
    private Collection<?> collection;

    public TableModelCollectionAdapter(Collection<?> collection) {
        this(collection, null);
    }

    public TableModelCollectionAdapter(Collection<?> collection, String title) {
        this.collection = collection;
        this.title = title;
    }

    @Override
    public String getCellContent(int x, int y) {
        Iterator<?> it = collection.iterator();

        while (y > 0) {
            it.next();
            y--;
        }

        return it.next().toString();
    }

    @Override
    public int getHeight() {
        return collection.size();
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public String getColumnTitle(int x) {
        return title;
    }
}
