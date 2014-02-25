/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

public class Quicksort extends IntegerSortBench {

    @Override
    public void run() {
        sort(array, 0, N - 1);
    }

    /**
     * @param array not null array of integer
     * @param lo index of the first sorted element
     * @param hi index of the last sorted element
     */
    static void sort(int[] array, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int pivot = array[hi], firstBig = lo,tmp;
        for (int i = lo; i < hi; i++) {            
            if ((tmp = array[i]) < pivot) {
                array[i] = array[firstBig];
                array[firstBig++] = tmp;
            }
        }
        array[hi] = array[firstBig];
        array[firstBig] = pivot;
        sort(array, lo, firstBig - 1);
        sort(array, firstBig + 1, hi);
    }
}
