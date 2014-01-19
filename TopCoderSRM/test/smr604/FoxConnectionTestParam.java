package smr604;

import java.io.IOException;
import java.util.Collection;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Pierre
 */
@RunWith(Parameterized.class)
public class FoxConnectionTestParam {

    private final int[] A, B;
    private final String fox;
    private final int expected;

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() throws IOException {
        return SystemTest.parseCaseForTest(FoxConnectionTestParam.class);
    }

    public FoxConnectionTestParam(int[] A, int[] B, String fox, int expected) {
        this.A = A;
        this.B = B;
        this.fox = fox;
        this.expected = expected;
    }

    @Test
    public void test_7() {
        assertEquals(expected, FoxConnection.minimalDistance(A, B, fox));
    }
}
