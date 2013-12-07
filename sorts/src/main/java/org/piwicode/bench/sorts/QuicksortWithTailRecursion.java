/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

public class QuicksortWithTailRecursion extends SortBench {

    @Override
    public void run() {
        sort(array, 0, N - 1);
    }

    static void sort(int[] array, int lo, int hi) {
        while (lo < hi) {
            int p = array[hi], firstBig = lo, t;
            for (int i = lo; i < hi; i++) {
                t = array[i];
                if (t < p) {
                    array[i] = array[firstBig];
                    array[firstBig++] = t;
                }
            }
            array[hi] = array[firstBig];
            array[firstBig] = p;
            sort(array, lo, firstBig - 1);
            lo = firstBig + 1;
        }
    }

    @Override
    public String toString() {
        return "Quicksort with tail recursion";

    }

}
