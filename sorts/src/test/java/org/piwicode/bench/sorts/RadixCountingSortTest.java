/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

import org.junit.Test;
import static org.junit.Assert.*;
import org.piwicode.bench.framework.IntegerArray;

/**
 *
 * @author Pierre
 */
public class RadixCountingSortTest {

    @Test
    public void testSort() {
        final int array[] = IntegerArray.shuffeledIdentity(1000);
        final int buffer[] = array.clone();
        final int buckets[] = new int[257];

        RadixCountingSort.sort(array, buffer, buckets);
        assertTrue(IntegerArray.isIdentity(array));
    }

}
