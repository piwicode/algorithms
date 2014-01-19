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
public class MiddleCodeTest {

    @Test
    public void testSomeMethod() {
        assertEquals("", MiddleCode.encode(""));
        assertEquals("a", MiddleCode.encode("a"));
        assertEquals("aa", MiddleCode.encode("aa"));
        assertEquals("ab", MiddleCode.encode("ab"));
        assertEquals("ab", MiddleCode.encode("ba"));
    }


}
