/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

import org.piwicode.bench.sorts.Mergesort;
import static org.piwicode.bench.sorts.IntegerSortBench.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.piwicode.bench.framework.IntegerArray;

/**
 *
 * @author Pierre
 */
public class MergesortTest {

    @Test
    public void testSort() {
        final int array[] = IntegerArray.shuffeledIdentity(1000);
        final int buffer[] = array.clone();
        Mergesort.sort(array, buffer, 0, array.length-1);
        assertTrue(IntegerArray.isIdentity(array));
    }

}
