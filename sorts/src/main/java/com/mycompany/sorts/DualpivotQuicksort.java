package com.mycompany.sorts;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class DualpivotQuicksort extends SortBench {

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
