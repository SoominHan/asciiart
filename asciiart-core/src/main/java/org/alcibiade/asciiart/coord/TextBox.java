package org.alcibiade.asciiart.coord;

import java.util.Iterator;

public class TextBox implements Iterable<TextCoord> {

    private TextCoord coord1;
    private TextCoord coord2;

    public TextBox(TextCoord tc1) {
        coord1 = new TextCoord(0, 0);
        coord2 = tc1;
    }

    public TextBox(int x1, int y1, int x2, int y2) {
        this(new TextCoord(x1, y1), new TextCoord(x2, y2));
    }

    public TextBox(TextCoord tc1, TextCoord tc2) {
        coord1 = new TextCoord(Math.min(tc1.getX(), tc2.getX()), Math.min(tc1.getY(), tc2.getY()));
        coord2 = new TextCoord(Math.max(tc1.getX(), tc2.getX()), Math.max(tc1.getY(), tc2.getY()));
    }

    public TextBox(TextBox... boxes) {
        coord1 = TextCoord.MAX_VALUE;
        coord2 = TextCoord.MIN_VALUE;

        for (TextBox box : boxes) {
            coord1 = coord1.minValues(box.coord1);
            coord2 = coord2.maxValues(box.coord2);
        }
    }

    public TextCoord getLowCoord() {
        return coord1;
    }

    public TextCoord getHighCoord() {
        return coord2;
    }

    public boolean isInside(TextCoord tc) {
        boolean result = coord1.getX() <= tc.getX()
                && tc.getX() < coord2.getX();
        result = result && coord1.getY() <= tc.getY()
                && tc.getY() < coord2.getY();
        return result;
    }

    public TextBoxSize getSize() {
        return new TextBoxSize(coord2.subtract(coord1));
    }

    @Override
    public Iterator<TextCoord> iterator() {
        return new TextBoxIterator();
    }

    private class TextBoxIterator implements Iterator<TextCoord> {

        private TextCoord current = coord1;

        @Override
        public boolean hasNext() {
            return current != null
                    && (current.getX() < coord2.getX() || current.getY() < coord2.getY());
        }

        @Override
        public TextCoord next() {
            TextCoord result = current;

            if (current != null) {
                if (current.getX() < coord2.getX() - 1) {
                    current = current.add(TextCoord.X);
                } else if (current.getY() < coord2.getY() - 1) {
                    current = new TextCoord(coord1.getX(), current.getY() + 1);
                } else {
                    current = null;
                }
            }

            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%s:%s", coord1, coord2);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        if (obj instanceof TextBox) {
            TextBox box = (TextBox) obj;
            result = coord1.equals(box.coord1) && coord2.equals(box.coord2);
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
        return 7 * coord1.hashCode() + 17 * coord2.hashCode();
    }
}
