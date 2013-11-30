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
public class RadixCountingSortTest {

    @Test
    public void testSort() {
        final int array[] = new int[1000];
        shuffle(array);
        setIdentity(array);
        final int buffer[] = array.clone();
        final int buckets[] = new int[257];
        
        RadixCountingSort.sort(array,buffer, buckets);
        assertTrue(isIdentity(array));
    }

}
