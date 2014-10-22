package net.magellanea.sudoku.solver;

import java.io.File;
import java.io.IOException;

/**
 * Created by yakoub on 22/10/14.
 */
public class Main {
    public static void main(String args[]) throws IOException {
        File f = new File("/home/yakoub/Downloads/sudoku");
        SudokuPuzzle puzzle = new SudokuPuzzle(f);
        puzzle.solve();;
        puzzle.export(new File("/home/yakoub/Downloads/sudoku_solution"));
    }
}
