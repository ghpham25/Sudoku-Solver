# *Sudoku-Solver*

## Features 
- [x] **Implement Sudoku using stack-based application and depth-first-search algorithm**
- [x] **Implemented tests to ensure the correctness of code**
- [x] **Allow user to input custom intial values number**
- [x] **Implement an advanced algorithm that that searches for the next best cell by judging its number of possible values, starting from the one with the lowest to highest number of possible values, increasing runtime by 300%** 
- [x] **Automate sudoku solver using user input**

## Solved Sudoku Board 
Note: Solved Sudoku board with number of initial values = 5 (input by user) 

Using Landscape Display:


<img width="289" alt="Screenshot 2024-02-08 at 14 21 56" src="https://github.com/ghpham25/Sudoku-Solver/assets/99609320/4d4f03fc-3e57-467d-8129-952f18963219">

Before and after values on terminal: 


<img width="283" alt="Screenshot 2024-02-08 at 14 23 13" src="https://github.com/ghpham25/Sudoku-Solver/assets/99609320/e2cab0a3-8bab-4a01-90b4-380aca561478">

## Analysis: 
### Exploration 1: Number of solved board decreases with number of initial values
<img width="597" alt="Screenshot 2024-02-08 at 14 27 20" src="https://github.com/ghpham25/Sudoku-Solver/assets/99609320/7bf6f76e-5bbd-4821-a7b4-984d98a5e517">

The above board is the result of running simulations of different boards with different initial values 10 times each and getting the number of times the board is solvable. For a board of 10 initial values, the board is always solvable, then it goes down to 60% for a board with 20 initial values, then goes to 0 for a board with 30 and 40 initial values. 

The reason is that the more initial values a board has, the more chance the numbers are aligned in ways that are impossible for the boards to find a valid solution. 

### Exploration 2: Comparision of 2 methods of finding next cell
<img width="579" alt="Screenshot 2024-02-08 at 14 29 19" src="https://github.com/ghpham25/Sudoku-Solver/assets/99609320/4fee6228-2ea6-4e7d-b74e-cbb03f0d10a0">

Compared to the first way (checking all cells from left to right), the second way is significantly faster because it reduces the number of options to go through in total to find the solution of the board. 

I implemented the algorithm by looping through the board and keeping track of the minimum option and the cell with the minimum number of valid options. I return the cell with the minimum number of options and return null if there is any cell with no valid option. I also created a reset method in the Sudoku class that resets all the unlocked values of the board to 0 while keeping all the locked values the same so that the comparison can be fairest. 

## How to run
cd into Sudoku-Solver folder

command: 
java Sudoku.java <number of wanted inital values>

