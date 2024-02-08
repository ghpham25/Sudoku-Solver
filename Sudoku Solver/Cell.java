/**
 * Giang Pham 
 * Mar 1, 2022
 * Project 3
 * Cell.java
 * create a cell
 */
import java.awt.Graphics;
public class Cell {
    private int row;
    private int col;
    private int value;
    private boolean isLocked;

    public Cell() {
        /**
         * constructor for cell
         */
        this.row = 0;
        this.col = 0;
        this.value = 0;
        this.isLocked = false;
    }

    public Cell(int row, int col, int value) {
        /**
         * constructor for cell
         */
        this.row = row;
        this.col = col;
        this.value = value;
        this.isLocked = false;
    }

    public Cell(int row, int col, int value, boolean lock) {
        /**
         * constructor for cell
         */
        this.row = row;
        this.col = col;
        this.value = value;
        this.isLocked = lock;
    }

    public int getRow() {
        /**
         * return the row position
         */
        return row;
    }

    public int getCol() {
        /**
         * return the column position
         */
        return col;
    }

    public int getValue() {
        /**
         * return the value
         */
        return value;
    }

    public void setValue(int newval) {
        /**
         * set the value to newval
         */
        value = newval;
    }

    public boolean isLocked() {
        /**
         * return if the cell is locked
         */
        return isLocked;
    }

    public void setLocked(boolean lock) {
         /**
         * set the isLocked field to lock
         */
        isLocked = lock;
    }

    public Cell clone() {
        /**
         * clone the cell
         */
        Cell newCell = new Cell(getRow(), getCol(), getValue(), isLocked());
        return newCell;
    }

    public String toString() {
        /**
         * String representation of cell
         */
        String toString = "" + value;
        return toString;
    }

    public void draw(Graphics g, int x0, int y0, int scale) {
        /**
         * draw the cell's number 
         */
        char[] draw = new char[2];
        draw[0] = (char)('0' + this.value);
        draw[1] = 0;
        g.drawChars(draw, 0, 1, x0 * scale, y0 * scale);
    }
}