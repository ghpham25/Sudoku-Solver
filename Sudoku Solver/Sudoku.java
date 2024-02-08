/**
 * Giang Pham 
 * Mar 1, 2022
 * Project 3
 * Sudoku.java
 * create the sudoku game
 */
import java.util.Random;
public class Sudoku {
    private Board board;
    private LandscapeDisplay display;

    public Sudoku() {
        /**
         * constructor for Sudoku
         */
        board = new Board();
        // display = new LandscapeDisplay(board, 30);
    }

    public Sudoku(int n) {
         /**
         * constructor for Sudoku
         */
        board = new Board();
        // display = new LandscapeDisplay(board, 30);
        Random rand = new Random();
        int count = 0;
        while (count < n) {
            int randRow = rand.nextInt(board.getRows()); // generate a random row position
            int randCol = rand.nextInt(board.getCols()); // generate a random column position
            int randValue = rand.nextInt(9) + 1; // generate a random value from 1 to 9
            if (board.get(randRow, randCol).getValue() == 0) {  // if there is no value in the random position
                if (board.validValue(randRow, randCol, randValue)) { // if the random value is valid in the random position
                    board.get(randRow, randCol).setValue(randValue); // set the value of the cell to the random value
                    board.get(randRow,randCol).setLocked(true); // lock the value
                    count += 1; //count += 1
                }
            }
        } 
    }

    public void reset() {
        //reset the board unlocked values to 0; keep the locked values
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!board.get(i, j).isLocked()) {
                    board.get(i,j).setValue(0);
                }
            }
        }
    }
    
    public boolean solve1(int delay) {
         /**
         * solve the board - finding best cell by method 1 (choose from left to right)
         */
        int numLocked = board.numLocked();
        CellStack cellStack = new CellStack();
        while (cellStack.size() < 81 - numLocked) {
            // System.out.println("hello!");
            Cell nextCellCheck = board.findBestCell1();
            if( delay > 0 ) {
                try {
                    Thread.sleep(delay);
                }
                catch(InterruptedException ex) {
                    System.out.println("Interrupted");
                }
                display.repaint();
            }
            if (nextCellCheck != null) {
                cellStack.push(nextCellCheck);
                board.set(nextCellCheck.getRow(), nextCellCheck.getCol(), nextCellCheck.getValue());
            } 
             else {
                while (cellStack.size() > 0) { // backtracking loop
                    // System.out.println("hello");
                    Cell cell = cellStack.pop();
                    boolean stuck = true;
                    for (int i = cell.getValue(); i<10; i++) {
                        if (board.validValue(cell.getRow(), cell.getCol(), i) && cell.getValue() != i) { // if there is another untested value this cell could try
                            cell.setValue(i);
                            cellStack.push(cell);
                            board.set(cell.getRow(), cell.getCol(), cell.getValue());
                            stuck = false;
                            break;
                        } 
                    } if (!stuck) {
                        break;
                    } else {
                        board.set(cell.getRow(), cell.getCol(), 0); // set the value of the popped cell to 0 and continue backtracking
                    }
                }
                if (cellStack.size() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean solve2(int delay) {
         /**
         * solve the board - finding best cell by method 2 (find cell with fewest possible value options)
         */
        int numLocked = board.numLocked();
        CellStack cellStack = new CellStack();
        while (cellStack.size() < 81 - numLocked) {
            Cell nextCellCheck = board.findBestCell2();
            if( delay > 0 ) {
                try {
                    Thread.sleep(delay);
                }
                catch(InterruptedException ex) {
                    System.out.println("Interrupted");
                }
                display.repaint();
            }
            if (nextCellCheck != null) {
                cellStack.push(nextCellCheck);
                board.set(nextCellCheck.getRow(), nextCellCheck.getCol(), nextCellCheck.getValue());
            } else if (cellStack.size() == 81 - numLocked) {
                return true;
            }
             else {
                while (cellStack.size() > 0) { // backtracking loop
                    Cell cell = cellStack.pop();
                    boolean stuck = true;
                    for (int i = cell.getValue(); i<10; i++) {
                        if (board.validValue(cell.getRow(), cell.getCol(), i) && cell.getValue() != i) { // if there is another untested value this cell could try
                            cell.setValue(i);
                            cellStack.push(cell);
                            board.set(cell.getRow(), cell.getCol(), cell.getValue());
                            stuck = false;
                            break;
                        } 
                    } if (!stuck) {
                        break;
                    } else {
                        board.set(cell.getRow(), cell.getCol(), 0); // set the value of the popped cell to 0 and continue backtracking
                    }
                }
                if (cellStack.size() == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        /**
         * main method for tests
         */
        String initValue = args[0];
        Sudoku testSudoku = new Sudoku(Integer.parseInt(initValue));
        // System.out.println("new board" + "\n" + testSudoku.board.toString());

        // 1st way of solving the board
        long startTime = System.nanoTime();
        boolean solved = testSudoku.solve1(0);
        long endTime = System.nanoTime();
        // System.out.println("solved board," + Integer.parseInt(initValue) + " initial values" + "\n" + testSudoku.board.toString());
        System.out.println("Run time in microsecond (1st way) " + Integer.parseInt(initValue) + " initial values: " + ((endTime - startTime)/1000000.0));

        // test solve
        if (!solved) {
            System.out.println("unsolved");
        }

        //2nd way of solving the board
        testSudoku.reset();
        System.out.println("reset test: " + "\n" + testSudoku.board.toString());
        long startTime2 = System.nanoTime();
        testSudoku.solve2(0);
        long endTime2 = System.nanoTime();
        System.out.println("solved board," + Integer.parseInt(initValue) + " initial values" + "\n" + testSudoku.board.toString());
        System.out.println("Run time in microsecond (2nd way): " + ((endTime2 - startTime2)/1000000.0));
    }
}
