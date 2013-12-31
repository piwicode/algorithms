/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

import org.piwicode.bench.framework.Result;
import org.piwicode.bench.framework.Session;

/**
 *
 * @author Pierre
 */
public class Main {

    public static void main(String[] args) {
        Session session = Session.create();

        session.let("class").beOneOf(
                Quicksort.class,
                QuicksortWithTailRecursion.class,
                Mergesort.class,
                DualpivotQuicksort.class,
                Heapsort.class,
                HybridQuicksort.class,
                RadixCountingSort.class,
                RadixBinarySort.class);

        Result result = session.run(1, 1);
        result.plotBarChart("Average elapsed time to sort one million integers", "class", "mean");
    }
}
