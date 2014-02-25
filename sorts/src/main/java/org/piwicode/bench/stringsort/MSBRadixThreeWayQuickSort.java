/*
 *  Simple preformance benchmark and algorythms try-out:
 *  https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.stringsort;

/**
 *
 * @author plabatut
 */
public class MSBRadixThreeWayQuickSort extends StringSortBench {

    @Override
    public void run() {
        sort(array, 0, array.length - 1, 0);
    }

    private static char charAt(String str, int pos) {
        return pos < str.length() ? str.charAt(pos) : (char) 0;
    }

    private static void swap(String[] array, int a, int b) {
        String t = array[a];
        array[a] = array[b];
        array[b] = t;
    }

    public static void sort(String[] array, final int lo, final int hi, int depth) {
        if (lo >= hi)
            return;
        char pivot = charAt(array[lo], depth);
        int fp = lo, lp = hi;
        for (int i = lo; i <= lp;) {
            char c = charAt(array[i], depth);
            if (c < pivot) swap(array, i++, fp++);
            else if (c > pivot) swap(array, i, lp--);
            else i++;
        }
        sort(array, lo, fp - 1, depth);
        if (pivot != 0) sort(array, fp, lp, depth + 1);
        sort(array, lp + 1, hi, depth);
    }

}
