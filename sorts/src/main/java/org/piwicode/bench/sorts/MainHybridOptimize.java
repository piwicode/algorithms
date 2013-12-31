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
public class MainHybridOptimize {

    public static void main(String[] args) {
        final Session session = Session.create();
        session.let("class").beEqualTo(HybridQuicksort.class);
        session.let("n").beEqualTo(100000);
        session.let("split").beOneOf(Sample.linear(2, 300, 1));
        session.run(2, 4).plot("Hybrid quicksort optimization", "class", "split", "mean");        
    }
}
