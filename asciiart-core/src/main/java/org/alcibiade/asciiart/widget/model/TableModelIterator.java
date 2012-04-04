package org.alcibiade.asciiart.widget.model;

import java.util.Iterator;

public class TableModelIterator implements Iterator<String> {

    private int x;
    private int y;
    private TableModel model;

    public TableModelIterator(TableModel model) {
        this.model = model;
        this.x = 0;
        this.y = 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.Iterator#hasNext()
     */
    @Override
    public boolean hasNext() {
        return y < model.getHeight();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.Iterator#next()
     */
    @Override
    public String next() {
        String data = null;

        if (hasNext()) {
            data = model.getCellContent(x, y);

            x += 1;

            if (x == model.getWidth()) {
                x = 0;
                y++;
            }
        }

        return data;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.Iterator#remove()
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
