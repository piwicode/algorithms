/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smr640div1;

import com.google.common.primitives.Ints;
import java.io.IOException;
import java.util.Arrays;
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
public class FoxConnection2Test {

    private final int[] A, B;
    private final int fox, expected;

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() throws IOException {
        return SystemTest.parseCaseForTest(FoxConnection2Test.class);
    }

    public FoxConnection2Test(int[] A, int[] B, int fox, int expected) {
        this.A = A;
        this.B = B;
        this.fox = fox;
        this.expected = expected;
    }

    @Test
    public void testSomeMethod() {
        System.out.print("A:");
        System.out.println(Ints.asList(A));
        System.out.print("B:");
        System.out.println(Ints.asList(B));
        System.out.print("Fox:");
        System.out.println(fox);
        assertEquals(expected, FoxConnection2.ways(A, B, fox));
    }

}
