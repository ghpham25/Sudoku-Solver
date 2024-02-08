/**
 * Giang Pham 
 * Mar 1, 2022
 * Project 3
 * Board.java
 * create a board for the sudoku
 */
import java.io.*;
import java.lang.Integer;
import java.awt.Graphics;

public class Board {
  private Cell[][] cellsArray;
  public static final int SIZE = 9;

    public Board() {
      /**
       * Board constructor, default cells in board are of value 0
       */
      cellsArray = new Cell[Board.SIZE][Board.SIZE];
      for (int i = 0; i < cellsArray.length; i++ ) {
        for (int j = 0; j < cellsArray[i].length; j++) {
          cellsArray[i][j] = new Cell(i, j, 0);
        }
      }
    }

    public String toString() {
      /**
       * represent the board in strings
       */
      String toString = "";
      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          if (j == 3 || j == 6){
            toString += " ";
          }
          toString += cellsArray[i][j].toString();
        } 
        if (i == 2 || i == 5) {
          toString += "\n"; 
        }
        toString += "\n";
      }
      return toString;
    }

    public int getCols() {
      /**
       * returns the number of columns
       */
      return SIZE;
    }

    public int getRows() {
      /**
       * returns the number of rows
       */
      return SIZE;
    }

    public Cell get(int r, int c) {
      /**
       * returns the Cell at location r, c
       */
      return cellsArray[r][c];
    }

    public boolean isLocked(int r, int c) {
      /**
       *  returns whether the Cell at r, c, is locked
       */
      if (cellsArray[r][c].isLocked()) 
        return true;
      else 
        return false;
    }

    public int numLocked() {
      /**
       * returns the number of locked Cells on the board
       */
      int count = 0;
      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          if (isLocked(i,j)) 
            count += 1;
        }
      }
      return count;
    }

    public int value(int r, int c) {
      /**
       * returns the value at Cell r, c
       */
      return cellsArray[r][c].getValue();
    }

    public void set(int r, int c, int value) {
      /**
       * sets the value of the Cell at r, c
       */
      cellsArray[r][c].setValue(value);
    }

    public void set(int r, int c, int value, boolean locked) {
      /**
       * sets the value and locked fields of the Cell at r, c
       */
      cellsArray[r][c].setValue(value);
      cellsArray[r][c].setLocked(locked);
    }

    public boolean validValue(int row, int col, int value) {
      /**
       * check if a given value is valid at positiion row, column
       */
      // check row 
      for (int j = 0; j < SIZE; j++) {
        if (j == col)
          continue;
        if (value < 1 || value > 9 || (cellsArray[row][j].getValue() == value)) {
          return false;
        }
      }

      //check column
      for (int i = 0; i < SIZE; i++) {
        if (i == row)
          continue;
        if (value < 1 || value > 9 || (cellsArray[i][col].getValue() == value)) {
          return false;
        }
      }

      //check block
      for (int i = (row/3) * 3; i < (row/3) * 3 + 3; i++) {
        for (int j = (col/3) * 3; j < (col/3) * 3 + 3; j ++) {
          if (j == col && i == row)
            continue;
          if (value < 1 || value > 9 || (cellsArray[i][j].getValue() == value)) {
            return false;
          }
        }
      }
      return true;
    }

    public boolean validSolution() {
      /**
       * check if the solution is valid
       */
      for (int i = 0; i < SIZE; i ++) {
        for (int j = 0; j < SIZE; j++) {
          if (!validValue(i, j, value(i, j)) || value(i, j) == 0) {
            return false;
          }
        }
      }
      return true;
    }

    public Cell findBestCell1() {
      /**scan the board by rows from the upper left to find the first cell with a 0 value. 
 */
      Cell bestCell;
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          if (get(i,j).getValue() == 0) {
            for (int v = 1; v < 10; v++) {
              if (validValue(i, j, v)) {
                bestCell = get(i,j);
                bestCell.setValue(v);
                return bestCell;
              }
            }
            return null;
          }}}
      return null;
    }

    public Cell findBestCell2() {
      /**
       * find best cell by finding the cell with fewest value options
       */
      // find the next best cell to check
      int minOptions = 9;
      Cell bestCell = null;
      // loop through each cell in the board
      for (int i = 0; i < getRows(); i++) {
        for (int j = 0; j < getCols(); j++) {
          // if (get(i, j).isLocked()){
          //   continue; //skip the locked cells} 
          if (get(i,j).getValue() == 0) {
            int numPossibleValues = 0;
            for (int v = 1; v < 10; v++) {
              if (validValue(i, j, v)) {
                numPossibleValues += 1; //count the number of possible values and update the variable
              }
            }
            if (numPossibleValues == 0) { //if there is a cell with no possible values, return null
              return null; 
            } else if (numPossibleValues <= minOptions) { 
              // if there is a cell with at least 1 possible value and 
              // its number of possible values is less than current minimum num of possible values                                                                              
              minOptions = numPossibleValues; //update the minOptions;
              bestCell = get(i,j); //update the best Cell
              for (int v = 1; v < 10; v++) { //set the value of bestCell to its first valid value
                if (validValue(i,j,v)) {
                  bestCell.setValue(v);
                  // return bestCell;
                }}}}
        }
      }
      return bestCell; 
    }

    public boolean read(String filename) {
      /**
       * read a file and load in values to board
       */
        try {
          // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
            FileReader fileReader = new FileReader(filename);
          // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
            BufferedReader bufferedReader = new BufferedReader(fileReader);
          // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
            String line = bufferedReader.readLine();
          // start a while loop that loops while line isn't null
          int countLine = 0;
          while (line != null) {
              String[] lines = line.split("[ ]+"); // assign to an array of type String the result of calling split on the line with the argument "[ ]+"
              // System.out.println(line); // print the String (line)
              // System.out.println("length of lines " + lines.length); // print the size of the String array (you can use .length)
                for (int j = 0; j < lines.length; j++){
                    set(countLine, j, Integer.parseInt(lines[j]));
                }
              line = bufferedReader.readLine(); // assign to line the result of calling the readLine method of your BufferedReader object.
              countLine +=1;
          } 
          bufferedReader.close(); // call the close method of the BufferedReader
          return true;
        }
        catch(FileNotFoundException ex) {
          System.out.println("Board.read():: unable to open file " + filename );
        }
        catch(IOException ex) {
          System.out.println("Board.read():: error reading file " + filename);
        }
        return false;
      }

      public void draw(Graphics g, int scale) {
        // draw the board
        for (int i = 0; i < getRows(); i++) {
          for (int j = 0; j < getCols(); j++) {
            get(i, j).draw(g, i + 1, j + 1, scale);
          }
        }

        g.drawLine(3 * scale + scale/2, 0 * scale + scale/2, 3 * scale + scale/2, 9 * scale + scale/2);
        g.drawLine(6 * scale + scale/2, 0 * scale + scale/2, 6 * scale + scale/2, 9 * scale + scale/2);
        g.drawLine(0 * scale + scale/2, 3 * scale + scale/2, 9 * scale + scale/2, 3 * scale + scale/2);
        g.drawLine(0 * scale + scale/2, 6 * scale + scale/2, 9 * scale + scale/2, 6 * scale + scale/2);
        }
        
      public static void main(String[] args) {
        /**
         * main method for tests
         */
          Board newBoard = new Board();

          //test read
          String arg = args[0];
          newBoard.read(arg); //load in the values from the text

          System.out.println(newBoard.toString());
          System.out.println(newBoard.validValue(1, 1, 4));
          System.out.println(newBoard.toString());
          System.out.println(newBoard.getCols());
          System.out.println(newBoard.getRows());

          newBoard.set(4, 4, 5);
          System.out.println(newBoard.value(4, 4));

          newBoard.set(3, 7, 6, true);
          newBoard.set(3, 4, 3, true);
          newBoard.set(3, 5, 2, true);

          System.out.println(newBoard.isLocked(3, 7));
          System.out.println(newBoard.numLocked());

      }
}
