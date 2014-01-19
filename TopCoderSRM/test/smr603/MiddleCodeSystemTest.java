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
public class MiddleCodeSystemTest {

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() throws IOException {
        return SystemTest.parseCaseForTest(MiddleCodeSystemTest.class);
    }

    private final String in, expected;

    public MiddleCodeSystemTest(String in, String expected) {
        this.in = in;
        this.expected = expected;
    }

    @Test
    public void testSomeMethod() {
        assertEquals(expected, MiddleCode.encode(in));
    }

}
