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
public class Main_Optimize_Quicksort_cutoff {

    public static void main(String[] args) {
        final Session session = Session.create();
        session.let("class").beEqualTo(HybridQuicksort.class);
        session.let("n").beEqualTo(100000);
        session.let("cutoff").beOneOf(Sample.linear(2, 250, 1));
        session.run(20,20).plot("Quick-sort cutoff optimization", "class",
                "cutoff: length of insertion sort runs", "mean: average elapsed time")
                .includeZero(false).show();
    }
}
