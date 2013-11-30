/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package ogr.piwicode.bench.sorts;

import static ogr.piwicode.bench.sorts.SortBench.isIdentity;
import static ogr.piwicode.bench.sorts.SortBench.setIdentity;
import static ogr.piwicode.bench.sorts.SortBench.shuffle;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Pierre
 */
public class HeapsortTest {

    @Test
    public void testSort() {
        final int array[] = new int[1000];
        shuffle(array);
        setIdentity(array);
        Heapsort.sort(array);
        assertTrue(isIdentity(array));
    }
}
