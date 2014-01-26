/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

/**
 * Hello world!
 *
 */
public class HybridQuicksort extends SortBench {

    int cutoff = 75;

    public void setCutoff(int insertLength) {
        this.cutoff = insertLength;
    }

    @Override
    public void run() {
        sort(array, 0, N - 1, cutoff);
        insertionsort(array, 0, N - 1);
    }

    static void sort(int[] array, int lo, int hi, int cutoff) {
        while (hi - lo >= cutoff) {
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
            sort(array, lo, firstBig - 1, cutoff);
            lo = firstBig + 1;
        }
    }

    /**
     * @param array not null array of integer
     * @param lo index of the first sorted element
     * @param hi index of the last sorted element
     */
    static void insertionsort(int[] array, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int v = array[i], j, tmp;
            for (j = i; j > lo && (tmp = array[j - 1]) > v; j--) {
                array[j] = tmp;
            }
            array[j] = v;
        }
    }

    @Override
    public String toString() {
        return "Quicksort with cutoff=" + cutoff;
    }

}
