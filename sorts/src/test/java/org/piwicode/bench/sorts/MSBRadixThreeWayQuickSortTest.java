/*
 *  Simple preformance benchmark and algorythms try-out:
 *  https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

import com.google.common.base.Charsets;
import org.piwicode.bench.stringsort.MSBRadixThreeWayQuickSort;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;
import org.piwicode.bench.stringsort.HybridMSBRadixThreeWayQuickSort;
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

    @Test
    public void testHybridRun() {
        int bucket[] = new int[257];
        final String[] array1 = Dictionary.sample(5000);

        byte[][] byteArray1 = new byte[array1.length][];
        byte[][] byteArray2 = new byte[array1.length][];
        for (int i = 0; i < byteArray1.length; i++) {
            byteArray1[i] = array1[i].getBytes(Charsets.UTF_8);
        }

        HybridMSBRadixThreeWayQuickSort.countingSort(byteArray1, byteArray2, 0, byteArray1.length - 1, 0, bucket);
        //HybridMSBRadixThreeWayQuickSort.quickSort(byteArray1,  0, byteArray1.length - 1, 0);
        
        final String[] array2 = array1.clone();
        for (int i = 0; i < array1.length; i++) {
            array2[i] = new String(byteArray1[i], Charsets.UTF_8);
        }

        Arrays.sort(array1);
        System.out.println(Arrays.toString(array2));
        assertArrayEquals(array1, array2);
    }

}
