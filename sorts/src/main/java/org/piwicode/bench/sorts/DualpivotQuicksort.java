/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class DualpivotQuicksort extends IntegerSortBench {

    @Override
    public void run() {
        sort(array);
    }

    static void sort(int[] array) {
        Arrays.sort(array);
    }

    @Override
    public String toString() {
        return "Dualpivot quicksort (builtin)";
    }

}
