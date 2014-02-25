/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

public class InsertionSortNaive extends IntegerSortBench {

    @Override
    public void run() {
        insertionsort(array, 0, N - 1);
    }

    /**
     * @param array not null array of integer
     * @param lo index of the first sorted element
     * @param hi index of the last sorted element
     */
    static void insertionsort(int[] array, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int val = array[i], j;
            for (j = i; j > lo && array[j - 1] > val; j--) {
                array[j] = array[j - 1];
            }
            array[j] = val;
        }
    }
}
