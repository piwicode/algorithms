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
public class MainComplexity {

    public static void main(String[] args) {
        final Session session = Session.create();
        session.let("class").beOneOf(
                InsertionSort.class,
                Quicksort.class,
                QuicksortWithTailRecursion.class,
                Mergesort.class,
                DualpivotQuicksort.class,
                Heapsort.class,
                HybridQuicksort.class,
                RadixCountingSort.class,
                NaiveRadixCountingSort.class,
                RadixBinarySort.class);
        session.let("n").beOneOf(Sample.linear(10, 1000, 10));

        session.run(1000, 4000).plot("", "class", "n","mean").show();        
    }
}
