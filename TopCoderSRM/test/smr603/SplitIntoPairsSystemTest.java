/*
 * Solution proposal to TopCoder Single Round Match
 */
package smr603;

import java.io.IOException;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import smr604.SystemTest;

/**
 *
 * @author Pierre
 */
@RunWith(Parameterized.class)
public class SplitIntoPairsSystemTest {

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() throws IOException {
        return SystemTest.parseCaseForTest(SplitIntoPairsSystemTest.class);
    }

    private final int[] n;
    private final int x, expected;

    public SplitIntoPairsSystemTest(int[] n, int x, int expected) {
        this.n = n;
        this.x = x;
        this.expected = expected;
    }

    @Test
    public void testSomeMethod() {
        assertEquals(expected, SplitIntoPairs.makepairs(n, x));
    }

}
