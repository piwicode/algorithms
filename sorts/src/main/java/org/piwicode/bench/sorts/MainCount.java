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
public class MainCount {

    public static void main(String[] args) {
        Session session = Session.create();

        session.let("class").beOneOf(
                Count.class
                );

        Result result = session.run(3, 3);
        result.plotBarChart("Average elapsed time to sum one billion integers", "class", "mean: average elapsed time").show();
    }
}
