/**
 * Giang Pham 
 * Mar 1, 2022
 * Project 3
 * CellStack.java
 * create a stack of cells 
 */
public class CellStack {
    private Cell[] cellStack;
    private int t;

    public CellStack() {
        /**
         * constructor for cellStack
         */
        cellStack = new Cell[9];
        t = 0;
    }

    public CellStack(int max) {
        /**
         * constructor for cellStack
         */
        cellStack = new Cell[max];
        t = 0;
    }

    public void push(Cell c) {
        /**
         * add a cell to the top of the stack
         */
        if (t < cellStack.length) {
            cellStack[t] = c;
            t++;
        } else {
            Cell[] newCellStack = new Cell[cellStack.length * 2];
            for (int i = 0; i < cellStack.length; i++) {
                newCellStack[i] = cellStack[i];
            }
            cellStack = newCellStack;
            cellStack[t] = c;
            t++;
        }
    }
    public int size() {
        /**
         * return size of stack
         */
        return t;
    }

    public Cell pop() {
        /**
         * remove and return the cell at the top of stack
         */
        if (size() > 0) {
            Cell removedCell = cellStack[t -1];
            t --;
            return removedCell;
        } else {
            return null;
        }
    }

    public boolean empty() {
        /**
         * find if the stack is empty
         */
        if (size() == 0) 
            return true;
        else 
            return false;
     }
    
}
