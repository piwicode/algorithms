/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

public class Quicksort extends SortBench {

    @Override
    public void run() {
        sort(array, 0, N - 1);
    }

    static void sort(int[] array, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
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
        sort(array, firstBig + 1, hi);
    }
}
