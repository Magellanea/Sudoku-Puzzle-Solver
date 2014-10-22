import net.magellanea.sudoku.solver.SudokuPuzzle;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Created by yakoub on 22/10/14.
 */
public class Main {

    public void testSudokuSolver() throws URISyntaxException {
        SudokuPuzzle problem = new SudokuPuzzle(
                new File(getClass().getResource("/testcase1problem").toURI()));
        SudokuPuzzle solution = new SudokuPuzzle(
                new File(getClass().getResource("/testcase1solution").toURI()));
        problem.solve();
        int [][] solutionGrid = solution.getSolution();
        int [][] solvedGrid = problem.getSolution();
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
                assert(solutionGrid[i][j]==solvedGrid[i][j]);
    }
    public static void main(String args[]) throws URISyntaxException {
        Main m = new Main();
        m.testSudokuSolver();
    }

}
