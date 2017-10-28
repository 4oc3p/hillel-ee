package hillelee.reflection;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProblemSolverTest {
    @Test
    public void solvePuzzle() throws Exception {
        String solve = new ProblemSolver().solve(new Puzzle());
        assertThat(solve, Matchers.is("Correct answer"));
    }

    @Test
    public void solveDecryption() throws Exception {
        String solve = new ProblemSolver().solve(new MessageDecryptor());
        assertThat(solve, Matchers.is("Correct answer"));
    }
}