/*
 *  Simple preformance benchmark and algorythms try-out:
 *  https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.stringsort;

import com.google.common.base.Charsets;
import java.util.Arrays;
import org.piwicode.bench.wordsets.Dictionary;

/**
 *
 * @author plabatut
 */
public class HybridMSBRadixThreeWayQuickSort extends StringSortBench {

    int bucket[] = new int[257];

    byte[][] byteArray;
    byte[][] byteArray2;

    @Override
    public void prepare() {
        array = Dictionary.sample(N);
        byteArray = new byte[array.length][];
        byteArray2 = new byte[array.length][];
        for (int i = 0; i < array.length; i++) {
            byteArray[i] = array[i].getBytes(Charsets.UTF_8);
        }
    }

    @Override
    public void run() {
        countingSort(byteArray, byteArray2, 0, array.length - 1, 0, bucket);
    }

    private static int charAt(byte[] str, int pos) {
        return pos < str.length ? str[pos] & 0xff : 0;
    }

    private static void swap(byte[][] array, int a, int b) {
        byte[] t = array[a];
        array[a] = array[b];
        array[b] = t;
    }

    public static void countingSort(byte[][] array, byte[][] buffer, final int lo, final int hi, int depth, int bucket[]) {
        if (lo >= hi) return;
        /*if (hi - lo < 100) {
         quickSort(array, lo, hi, depth);
         return;
         }*/
        Arrays.fill(bucket, 0);
        for (int i = lo; i <= hi; i++) {
            final int idx = charAt(array[i], depth);
            bucket[idx + 1]++;
        }
        if (bucket[1] == hi + 1) return;
        bucket[0] += lo;
        for (int i = 1; i < bucket.length; i++) {
            bucket[i] += bucket[i - 1];
        }

        for (int i = lo; i <= hi; i++) {
            final int idx = charAt(array[i], depth);
            buffer[bucket[idx]++] = array[i];
        }
        System.arraycopy(buffer, lo, array, lo, hi - lo + 1);

        int prev = 0;
        for (int i = 0; i < bucket.length; i++) {
            countingSort(array, buffer, prev, bucket[i] - 1, depth + 1, bucket);
            prev = bucket[i];
        }
    }

    public static void quickSort(byte[][] array, final int lo, final int hi, int depth) {
        if (lo >= hi) return;
        int pivot = charAt(array[lo], depth);
        int fp = lo, lp = hi;
        for (int i = lo; i <= lp;) {
            int c = charAt(array[i], depth);
            if (c < pivot) swap(array, i++, fp++);
            else if (c > pivot) swap(array, i, lp--);
            else i++;
        }
        quickSort(array, lo, fp - 1, depth);
        if (pivot != 0) quickSort(array, fp, lp, depth + 1);
        quickSort(array, lp + 1, hi, depth);
    }

}
