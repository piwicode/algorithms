/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.stringsort;

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
                MSBRadixThreeWayQuickSort.class,
                JdkSort.class
        );

        Result result = session.run(5, 5);
        result.plotBarChart("Average elapsed time to sort one million string", "class", "mean").show();
    }
}
