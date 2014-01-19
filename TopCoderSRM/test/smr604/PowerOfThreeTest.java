/*
 * Solution proposal to TopCoder Single Round Match
 */
package smr604;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class PowerOfThreeTest {

    public PowerOfThreeTest() {
    }

    @Test
    public void testByRef() {

        for (int i = 0; i <= 200; i++) {
            for (int j = 0; j <= 200; j++) {
                assertEquals("(" + i + "," + j + ")", PowerOfThreeRef.ableToGet(i, j), PowerOfThree.ableToGet(i, j));
            }
        }
    }

    @Test
    public void testSomeMethod() {
        assertEquals("Possible", PowerOfThree.ableToGet(0, 2));
        assertEquals("Impossible", PowerOfThree.ableToGet(1, 1));
        assertEquals("Impossible", PowerOfThree.ableToGet(3, 0));
        assertEquals("Impossible", PowerOfThree.ableToGet(1, 9));
        assertEquals("Possible", PowerOfThree.ableToGet(1, 3));
        assertEquals("Possible", PowerOfThree.ableToGet(0, 0));
        assertEquals("Possible", PowerOfThree.ableToGet(-6890, 18252));
        assertEquals("Impossible", PowerOfThree.ableToGet(-1000000000, -1000000000));
    }

}
