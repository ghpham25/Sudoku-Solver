/**
 * Giang Pham 
 * Mar 1, 2022
 * Project 3
 * Automate.java
 * Automate the process of exploring the trends in simulation
 */
import java.io.*;


public class Automate {
    public static void main(String[] args) {
        /**
         * simulate and calculate the average time run using user input (number of inital values and number of simulations)
         */
        String initValue = args[0];
        String numSim = args[1];

        int initValueInt = Integer.parseInt(initValue);
        int numSimInt = Integer.parseInt(numSim);

        int i = 0;

        long totalTime = 0;

        while (i < numSimInt) {
            Sudoku newSudoku = new Sudoku(initValueInt);
            long startTime = System.nanoTime();
            boolean solved = newSudoku.solve1(0);
            long endTime = System.nanoTime();
            long runTime = endTime - startTime;

            if (solved) {
                totalTime += runTime;
                i ++;
            } else {
                System.out.println("unsolved");
            }
        }
        double averageTime = (totalTime/1000000.0)/numSimInt;

        //write data to file
        try {
            FileWriter fw = new FileWriter("extension.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            fw.write("Average running time of a board with " + initValue + " initial values in " + numSim+ " simulations: " + averageTime);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        }
    }

