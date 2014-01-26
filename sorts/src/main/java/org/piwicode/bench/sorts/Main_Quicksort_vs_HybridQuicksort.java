/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

import org.piwicode.bench.framework.Session;

/**
 *
 * @author Pierre
 */
public class Main_Quicksort_vs_HybridQuicksort {

    public static void main(String[] args) {
        Session session = Session.create();

        session.let("class").beOneOf(
                Quicksort.class,
                HybridQuicksort.class);        
        session.run(40, 60).plotBarChart("Average elapsed time to sort one million integers",
                "name:sort algorythm", "mean:average elapsed time").show();
        
    }
}
