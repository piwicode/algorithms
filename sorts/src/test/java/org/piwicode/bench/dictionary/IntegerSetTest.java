/*
 *  Simple preformance benchmark and algorythms try-out:
 *  https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.dictionary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class IntegerSetTest {

    public IntegerSetTest() {
    }

    @Test
    public void testAdd() {
        Random r = new Random();
        HashSet<Integer> ref = new HashSet<>();
        IntegerSet set = new IntegerSet();

        for (int i = 0; i < 400000; i++) {
            int v = r.nextInt(100000)-50000;
            if (v == Integer.MIN_VALUE) {
                continue;
            }            
            assertEquals(ref.add(v), set.add(v));
        }
    }

}
