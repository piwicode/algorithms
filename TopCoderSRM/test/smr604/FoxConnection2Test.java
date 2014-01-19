package smr604;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class FoxConnection2Test {

    @Test
    public void testSomeMethod() {
        assertEquals(3, FoxConnection2.ways(new int[]{1, 2, 3}, new int[]{2, 3, 4}, 2));
    }

}
