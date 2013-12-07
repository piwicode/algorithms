/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

import org.piwicode.bench.sorts.HybridQuicksort;
import static org.piwicode.bench.sorts.SortBench.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.piwicode.bench.framework.IntegerArray;

/**
 *
 * @author Pierre
 */
public class HybridQuicksortTest {

    @Test
    public void testSort() {
        final int array[] = IntegerArray.shuffeledIdentity(1000);
        HybridQuicksort.sort(array, 0, array.length - 1, 55);
        assertTrue(IntegerArray.isIdentity(array));
    }

}
