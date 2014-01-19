/*
 * Solution proposal to TopCoder Single Round Match
 */
package smr603;

import java.io.IOException;
import java.util.Collection;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import smr604.SystemTest;

/**
 *
 * @author Pierre
 */
@RunWith(Parameterized.class)
public class GraphWalkWithProbabilitiesSystemTest {
    @Parameterized.Parameters
    public static Collection<Object[]> parameters() throws IOException {
        return SystemTest.parseCaseForTest(GraphWalkWithProbabilitiesSystemTest.class);
    }
    private final String[] graph;
    private final int[] winprob;
    private final int[] loseprob;
    private final int start;
    private final double expected;

    public GraphWalkWithProbabilitiesSystemTest(String[] graph, int[] winprob, int[] loseprob, int start, double expected) {
        this.graph = graph;
        this.winprob = winprob;
        this.loseprob = loseprob;
        this.start = start;
        this.expected = expected;
    }

    @Test
    public void testFindprob() {
        final double p = GraphWalkWithProbabilities.findprob(
                graph, winprob, loseprob, start);
        assertEquals(expected, p, 1e-10);
    }

}
