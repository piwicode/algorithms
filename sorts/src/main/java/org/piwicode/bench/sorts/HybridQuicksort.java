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

    int split = 55;

    public void setSplit(int insertLength) {
        this.split = insertLength;
    }

    @Override
    public void run() {
        sort(array, 0, N - 1, split);
    }

    static void sort(int[] array, int lo, int hi, int X) {
        while (hi - lo >= X) {
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
            sort(array, lo, firstBig - 1, X);
            lo = firstBig + 1;
        }
        //Insertion
        for (int i = lo + 1; i <= hi; i++) {
            int v = array[i], j;
            for (j = i; j > lo && array[j - 1] > v; j--) {
                array[j] = array[j - 1];
            }
            array[j] = v;
        }
    }

    @Override
    public String toString() {
        return "Hybrid quicksort(split=" + split + ")";
    }

}
