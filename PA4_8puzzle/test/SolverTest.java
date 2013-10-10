import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 4: 8 Puzzle
 */
/**
 *
 * @author Pierre
 */
@RunWith(Parameterized.class)
public class SolverTest {

    private final Board initial;
    private final ArrayList<Board> ref;

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"puzzle00.txt"},
            {"puzzle01.txt"},
            {"puzzle02.txt"},
            {"puzzle03.txt"},
            {"puzzle04.txt"},
            {"puzzle05.txt"},
            {"puzzle06.txt"},
            {"puzzle07.txt"},
            {"puzzle08.txt"},
            {"puzzle2x2-unsolvable1.txt"},
            {"puzzle2x2-unsolvable2.txt"},
            {"puzzle2x2-unsolvable3.txt"},
            {"puzzle3x3-unsolvable.txt"},
            {"puzzle3x3-unsolvable1.txt"},
            {"puzzle3x3-unsolvable2.txt"},
            {"puzzle4x4-unsolvable.txt"},
        });
    }

    public SolverTest(String inputName) {

        final In refIn = new In(getClass().getResource(inputName));
        final int stepCount = refIn.readInt();
        if (stepCount != 0) {
            ref = new ArrayList<Board>();
            for (int i = 0; i < stepCount; i++) {
                ref.add(readBoard(refIn));
            }
            initial = ref.get(0);
        } else {
            initial = readBoard(refIn);
            ref = null;
        }
        refIn.close();
    }

    @Test
    public void solve() {
        final Solver solver = new Solver(initial);
        if (ref == null) {
            Assert.assertFalse(solver.isSolvable());
            Assert.assertNull(solver.solution());
            Assert.assertEquals(-1, solver.moves());
        } else {
            Assert.assertTrue(solver.isSolvable());
            Assert.assertEquals(ref.size() - 1, solver.moves());
            final Iterator<Board> itr = solver.solution().iterator();
            int s = 0;
            for (Board step : ref) {
                s++;
                Assert.assertTrue("Step " + s + " - ", itr.hasNext());
                Assert.assertEquals("Step " + s + " - ", step, itr.next());
            }
            Assert.assertFalse(itr.hasNext());
        }
    }

    private Board readBoard(final In in) {
        final int N = in.readInt();
        final int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                blocks[i][j] = in.readInt();
            }
        }        
        return  new Board(blocks);
    }
}
