/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

import org.piwicode.bench.framework.Result;
import org.piwicode.bench.framework.Sample;
import org.piwicode.bench.framework.Session;

/**
 *
 * @author Pierre
 */
public class MainDupplicate {

    public static void main(String[] args) {
        final Session session = Session.create();
        session.let("class").beOneOf(
                Quicksort.class,
                QuicksortWithTailRecursion.class,
                Mergesort.class,
                DualpivotQuicksort.class,
                Heapsort.class,
                HybridQuicksort.class,
                RadixCountingSort.class,
                RadixBinarySort.class);
        session.let("n").beOneOf(Sample.linear(0., 1., .1));

        final Result run = session.run(5, 200);
        run.showCSV();
    }
}
