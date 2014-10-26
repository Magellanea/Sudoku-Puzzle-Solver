import net.magellanea.sudoku.solver.SudokuPuzzle;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by yakoub on 22/10/14.
 */
public class Main {

    @Test
    public void testSudokuSolver() throws URISyntaxException {
        // The number of test cases puzzles
        for(int k=1;k<=3;k++) {
            SudokuPuzzle problem = new SudokuPuzzle(
                    new File(getClass().getResource((String.format("/testcase%dproblem", k))).toURI()));
            SudokuPuzzle solution = new SudokuPuzzle(
                    new File(getClass().getResource((String.format("/testcase%dsolution",k))).toURI()));
            problem.solve();
            int[][] solutionGrid = solution.getSolution();
            int[][] solvedGrid = problem.getSolution();
            assertArrayEquals(solutionGrid, solvedGrid);
        }
    }
}
