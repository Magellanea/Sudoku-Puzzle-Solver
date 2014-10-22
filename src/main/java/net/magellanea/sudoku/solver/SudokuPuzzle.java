package net.magellanea.sudoku.solver;

import java.io.*;

/**
 * Created by yakoub on 22/10/14.
 */
public class SudokuPuzzle {
    // Stores the elements of the puzzle
    private int[][] grid = new int[9][9];
    // valueTakenAtRow[i][k]: returns if one of the entries
    //                        of row i is equal to k+1 or not.
    private boolean[][] valueTakenAtRow = new boolean[9][9];
    // valueTakenAtRow[i][k]: returns if one of the entries
    //                        of column i is equal to k+1 or not.
    private boolean[][] valueTakenAtColumn = new boolean[9][9];
    // valueTakenAtRow[i][k]: returns if one of the entries
    //                        of block i is equal to k+1 or not
    //                        The blocks are numbered from left
    //                        to right, the top left is of index 0.
    private boolean[][] valueTakenAtBlock = new boolean[9][9];

    /**
     * @param f the file containing the Sudoku puzzle to be solved
     *          with 0 representing missing values.
     */
    public SudokuPuzzle(File f) {
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(f));
            String s = null;
            int rowsCount = 0;
            while ((s = reader.readLine()) != null) {
                String[] elements = s.split(",");
                assert (elements.length == 9);
                for (int i = 0; i < 9; i++) {
                    int element = Integer.parseInt(elements[i]);
                    grid[rowsCount][i] = element;
                    if (element > 0) {
                        valueTakenAtColumn[i][element - 1] = true;
                        valueTakenAtRow[rowsCount][element - 1] = true;
                        int blockId = (i / 3) + (rowsCount / 3) * 3;
                        valueTakenAtBlock[blockId][element - 1] = true;
                    }
                }
                rowsCount++;
            }
            assert (rowsCount == 9);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A recursive method that solve the puzzle starting at
     * a given row and column
     *
     * @param row    The row to start searching from.
     * @param column The column to start searching from.
     * @return whether a solution can be found or not
     */
    private boolean solve(int row, int column) {
        assert ((row >= 0) && (row < 9));
        assert ((column >= 0) && (column < 9));
        int nextMissingElementRow = -1;
        int nextMissingElementColumn = -1;
        int currentIdx = (row * 9) + column;

        while ((currentIdx) < (9 * 9)) {
            int rowIdx = (currentIdx / 9);
            int columnIdx = (currentIdx % 9);
            if (grid[rowIdx][columnIdx] == 0) {
                nextMissingElementRow = rowIdx;
                nextMissingElementColumn = columnIdx;
                break;
            }
            currentIdx++;
        }
        // No other missing values, so that's is a solution! (hopefully)

        if (nextMissingElementRow == -1)
            return true;
        int nextMissingBlock = (nextMissingElementColumn / 3) + (nextMissingElementRow / 3) * 3;
        int nextColumn = ((nextMissingElementColumn + 1) % 9);
        int nextRow = (nextMissingElementColumn == 8 ? (nextMissingElementRow + 1) % 9 : nextMissingElementRow);
        for (int i = 0; i < 9; i++) {
            if ((!valueTakenAtBlock[nextMissingBlock][i])
                    && (!valueTakenAtColumn[nextMissingElementColumn][i])
                    && (!valueTakenAtRow[nextMissingElementRow][i])) {
                grid[nextMissingElementRow][nextMissingElementColumn] = i + 1;
                valueTakenAtColumn[nextMissingElementColumn][i] = true;
                valueTakenAtRow[nextMissingElementRow][i] = true;
                valueTakenAtBlock[nextMissingBlock][i] = true;
                // THe last element, if a solution found, the puzzle is solved !
                if ((nextColumn == 0) && (nextRow == 0))
                    return true;
                if (solve(nextRow, nextColumn))
                    return true;
                else {
                    valueTakenAtColumn[nextMissingElementColumn][i] = false;
                    valueTakenAtRow[nextMissingElementRow][i] = false;
                    valueTakenAtBlock[nextMissingBlock][i] = false;
                }
            }
        }
        // If I got here, no solution was found, so backtrack :(
        grid[nextMissingElementRow][nextMissingElementColumn] = 0;
        return false;
    }

    /**
     * Solves The SudokuPuzzle
     */
    public void solve() {
        this.solve(0, 0);
    }

    /**
     * Exports the SudokuPuzzle represented by this object
     * Note that if this is called before calling ```solve```, you
     * will get the main unsolved puzzle in the export file
     *
     * @param f The file to export to.
     */
    public void export(File f) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(f));
        for (int i = 0; i < 9; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < 8; j++)
                builder.append(String.format("%d,", grid[i][j]));
            builder.append(String.format("%d", grid[i][8]));
            pw.println(builder.toString());
        }
        pw.close();
    }

    /**
     * Exports the result
     * Note that if this is called before calling ```solve```, you
     * will get the main unsolved puzzle in the export file
     *
     * @return int[][] containing the puzzle solution
     */
    public int[][] getSolution() {
        return grid;
    }
}
