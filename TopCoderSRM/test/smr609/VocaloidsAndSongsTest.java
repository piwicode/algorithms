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
public class VocaloidsAndSongsTest {

    public VocaloidsAndSongsTest() {
    }

    @Test
    public void testCount() {
        assertEquals(0, VocaloidsAndSongs.count(1, 0, 0, 0));
        assertEquals(6, VocaloidsAndSongs.count(3, 1, 1, 1));
        assertEquals(9, VocaloidsAndSongs.count(3, 3, 1, 1));
        assertEquals(0, VocaloidsAndSongs.count(50, 10, 10, 10));
        assertEquals(81451692, VocaloidsAndSongs.count(18, 12, 8, 9));
        assertEquals(198591037, VocaloidsAndSongs.count(50, 25, 25, 25));
        for (int i = 1; i < 50; i++) {
            assertEquals(1, VocaloidsAndSongs.count(i, i, 0, 0));
            assertEquals(i, VocaloidsAndSongs.count(i, i, 0, 1));
            assertEquals(i * i, VocaloidsAndSongs.count(i, i, 1, 1));
        }
    }

}
