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
public class RadixBinarySortTest {

    @Test
    public void testSort() {
       final int array[] = IntegerArray.shuffeledIdentity(1000);
        final int buffer[] = array.clone();
        
        RadixBinarySort.sort(array,buffer);
        assertTrue(IntegerArray.isIdentity(array));
    }

}
