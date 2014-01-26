/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

import org.piwicode.bench.framework.Sample;
import org.piwicode.bench.framework.Session;

/**
 *
 * @author Pierre
 */
public class Main_Quicksort_vs_Insertionsort {

    public static void main(String[] args) {
        Session session = Session.create();

        session.let("class").beOneOf(
                Quicksort.class,
                InsertionSort.class);
        session.let("n").beOneOf(Sample.linear(10, 550, 10));
        session.run(400, 400).plot("Performance of quick-sort and insertion sort according to the array size", "class", "n:number of sorted element","mean:average elapsed time").show();     
    }
}
