/*
 * Solution proposal to TopCoder Single Round Match
 */
package smr609;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class PackingBallsDiv2Test {

    public PackingBallsDiv2Test() {
    }

    @Test
    public void testMinPacks() {
        assertEquals(4, PackingBallsDiv2.minPacks(4, 2, 4));
        assertEquals(100, PackingBallsDiv2.minPacks(100, 100, 100));
        assertEquals(4, PackingBallsDiv2.minPacks(2, 3, 5));
        for (int i = 1; i < 100; i++) {
            assertEquals(i, PackingBallsDiv2.minPacks(i, i, i));
            assertEquals(i + 1, PackingBallsDiv2.minPacks(i, i, i + 1));
            assertEquals((i / 3 * 2) + (i % 3), PackingBallsDiv2.minPacks(i, i, 0));
        }
    }

}
