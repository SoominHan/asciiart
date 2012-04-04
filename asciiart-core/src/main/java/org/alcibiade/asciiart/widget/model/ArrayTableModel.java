package org.alcibiade.asciiart.widget.model;

public class ArrayTableModel extends AbstractTableModel {

    private String[][] contents;

    public ArrayTableModel(String[][] contents) {
        assert contents != null;
        this.contents = contents;
    }

    @Override
    public String getCellContent(int x, int y) {
        String content = contents[x][y];

        if (content == null) {
            content = "NULL";
        }

        return content;
    }

    @Override
    public int getHeight() {
        return contents.length == 0 ? 0 : contents[0].length;
    }

    @Override
    public int getWidth() {
        return contents.length;
    }
}
