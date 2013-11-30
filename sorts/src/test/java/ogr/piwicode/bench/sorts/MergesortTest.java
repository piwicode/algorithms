/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package ogr.piwicode.bench.sorts;

import static ogr.piwicode.bench.sorts.SortBench.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class MergesortTest {

    @Test
    public void testSort() {
        final int array[] = new int[1000];
        shuffle(array);
        setIdentity(array);
        final int buffer[] = array.clone();
        Mergesort.sort(array, buffer, 0, array.length-1);
        assertTrue(isIdentity(array));
    }

}
