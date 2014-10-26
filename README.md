**Sudoku Puzzle solver**

This is a recursive implementation of a backtracking based sudoku puzzle solver, to be able to use it, create a (csv) file containing the problem for example:

```
0,3,5,2,9,0,8,6,4
0,8,2,4,1,0,7,0,3
7,6,4,3,8,0,0,9,0
2,1,8,7,3,9,0,4,0
0,0,0,8,0,4,2,3,0
0,4,3,0,5,2,9,7,0
4,0,6,5,7,1,0,0,9
3,5,9,0,2,8,4,1,7
8,0,0,9,0,0,5,2,6
```

Create a ```SudokuPuzzle``` Object using the ```File``` object containing your input

```
SudokuPuzzle puzzle = new SudokuPuzzle(new File("/path/to/puzzle.csv"));
```

Now call the ```solve``` method to be able to solve the puzzle

```
puzzle.solve();
// To get the solution as an array.
int[][] grid = puzzle.getSolution();
// To export the result to a file
puzzle.export(new File("/path/to/puzzle_sol.csv"));
```

note that calling ```getSolution()``` before ```solve``` will return the input matrix.

This is a **maven** based application, to be able to run the test cases run ```mvn test```
