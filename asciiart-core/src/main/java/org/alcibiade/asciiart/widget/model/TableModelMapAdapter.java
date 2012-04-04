package org.alcibiade.asciiart.widget.model;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TableModelMapAdapter extends AbstractTableModel {

    private String keyTitle;
    private String valueTitle;
    private Map<?, ?> map;

    public TableModelMapAdapter(Map<?, ?> collection) {
        this(collection, null, null);
    }

    public TableModelMapAdapter(Map<?, ?> collection, String... titles) {
        this(collection, titles[0], titles[1]);
    }

    public TableModelMapAdapter(Map<?, ?> collection, String keyTitle,
            String valueTitle) {
        this.map = collection;
        this.keyTitle = keyTitle;
        this.valueTitle = valueTitle;
    }

    @Override
    public String getCellContent(int x, int y) {
        Set<?> keySet = map.keySet();
        Iterator<?> it = keySet.iterator();

        while (y > 0) {
            it.next();
            y--;
        }

        Object resultObject = null;
        Object key = it.next();

        if (x == 0) {
            resultObject = key;
        } else {
            resultObject = map.get(key);
        }

        return resultObject.toString();
    }

    @Override
    public int getHeight() {
        return map.size();
    }

    @Override
    public int getWidth() {
        return 2;
    }

    @Override
    public String getColumnTitle(int x) {
        String title = null;

        if (x == 0) {
            title = keyTitle;
        } else if (x == 1) {
            title = valueTitle;
        }

        return title;
    }
}
