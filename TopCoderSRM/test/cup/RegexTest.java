/*
 * Solution proposal to TopCoder Single Round Match
 */
package cup;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class RegexTest {

    public RegexTest() {
    }

    @Test
    public void testSomeMethod() {

        assertEquals(1, Regex.countMatch("ab.*c.*d", "abcd"));
        assertEquals(1, Regex.countMatch("ab.*c.*d", "abzcd"));
        assertEquals(1, Regex.countMatch("ab.*c.*d", "abbzcd"));
        assertEquals(2, Regex.countMatch("ab.*c.*d", "abbzccd"));
        assertEquals(4, Regex.countMatch("ab.*c.*d", "abbzccdd"));
    }

}
