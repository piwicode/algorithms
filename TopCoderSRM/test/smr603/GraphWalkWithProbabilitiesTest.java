/*
 * Solution proposal to TopCoder Single Round Match
 */
package smr603;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class GraphWalkWithProbabilitiesTest {

    @Test
    public void testFindprob() {
        assertEquals(.5, GraphWalkWithProbabilities.findprob(
                new String[]{"1"},
                new int[]{1},
                new int[]{1},
                0), 0.);
    }

    @Test
    public void testFindprob2() {
        assertEquals(.6, GraphWalkWithProbabilities.findprob(
                new String[]{"11", "11"},
                new int[]{60, 40},
                new int[]{40, 60},
                0), 0.);
    }

}
