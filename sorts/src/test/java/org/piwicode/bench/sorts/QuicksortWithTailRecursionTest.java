/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

import org.piwicode.bench.sorts.QuicksortWithTailRecursion;
import static org.piwicode.bench.sorts.SortBench.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.piwicode.bench.framework.IntegerArray;

/**
 *
 * @author Pierre
 */
public class QuicksortWithTailRecursionTest {

    @Test
    public void testSort() {
      final int array[] = IntegerArray.shuffeledIdentity(1000);
        QuicksortWithTailRecursion.sort(array, 0, array.length - 1);
        assertTrue(IntegerArray.isIdentity(array));
    }
}
