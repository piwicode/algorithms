/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.piwicode.bench.framework.IntegerArray;

/**
 *
 * @author Pierre
 */
public class HeapsortTest {

    @Test
    public void testSort() {
       final int array[] = IntegerArray.shuffeledIdentity(1000);
        Heapsort.sort(array);
        assertTrue(IntegerArray.isIdentity(array));
    }
}
