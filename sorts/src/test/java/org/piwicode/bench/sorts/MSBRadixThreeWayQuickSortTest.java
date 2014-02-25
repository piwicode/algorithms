/*
 *  Simple preformance benchmark and algorythms try-out:
 *  https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

import org.piwicode.bench.stringsort.MSBRadixThreeWayQuickSort;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;
import org.piwicode.bench.wordsets.Dictionary;

/**
 *
 * @author plabatut
 */
public class MSBRadixThreeWayQuickSortTest {

    @Test
    public void testRun() {
        final String[] array1 = Dictionary.sample(50000);
        final String[] array2 = array1.clone();

        Arrays.sort(array1);
        MSBRadixThreeWayQuickSort.sort(array2, 0, array2.length - 1, 0);
        assertArrayEquals(array1, array2);
    }

}
