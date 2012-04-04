package org.alcibiade.asciiart.coord;

public class TextCoord implements Comparable<TextCoord> {

    public static final TextCoord ORIGIN = new TextCoord(0, 0);
    public static final TextCoord X = new TextCoord(1, 0);
    public static final TextCoord Y = new TextCoord(0, 1);
    public static final TextCoord ONE = new TextCoord(1, 1);
    public static final TextCoord MAX_VALUE = new TextCoord(Integer.MAX_VALUE,
            Integer.MAX_VALUE);
    public static final TextCoord MIN_VALUE = new TextCoord(Integer.MIN_VALUE,
            Integer.MIN_VALUE);
    private int x;
    private int y;

    public TextCoord(int x, int y) {
        super();

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TextCoord add(TextCoord coord) {
        return new TextCoord(x + coord.x, y + coord.y);
    }

    public TextCoord subtract(TextCoord coord) {
        return new TextCoord(x - coord.x, y - coord.y);
    }

    public TextCoord maxValues(TextCoord coord) {
        return new TextCoord(Math.max(x, coord.x), Math.max(y, coord.y));
    }

    public TextCoord minValues(TextCoord coord) {
        return new TextCoord(Math.min(x, coord.x), Math.min(y, coord.y));
    }

    public TextCoord invert() {
        return new TextCoord(-x, -y);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        boolean result = false;

        if (o instanceof TextCoord) {
            TextCoord coord = (TextCoord) o;
            result = (x == coord.x && y == coord.y);
        }

        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return 1039 * y + x;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(TextCoord coord) {
        int result = y - coord.y;

        if (result == 0) {
            result = x - coord.x;
        }

        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%d,%d", x, y);
    }
}
