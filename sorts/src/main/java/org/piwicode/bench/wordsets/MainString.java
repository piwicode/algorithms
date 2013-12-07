/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.wordsets;

import org.piwicode.bench.framework.Result;
import org.piwicode.bench.framework.Session;

/**
 *
 * @author Pierre
 */
public class MainString {

    public static void main(String[] args) {
        final Session session = Session.create();
        session.let("class").beOneOf(
                CompactTriBench.class,
                CompactTriHybridBench.class,
                ObjectTriBench.class,
                HashSetBench.class,
                ImmutableHashSetBench.class
                );

        final Result run = session.run(5, 50);
        run.showBests();
    }
}
