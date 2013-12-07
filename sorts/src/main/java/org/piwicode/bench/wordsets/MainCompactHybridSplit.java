/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.wordsets;

import org.piwicode.bench.framework.Result;
import org.piwicode.bench.framework.Sample;
import org.piwicode.bench.framework.Session;

/**
 *
 * @author Pierre
 */
public class MainCompactHybridSplit {

    public static void main(String[] args) {
        
        final Session session = Session.create();
        session.let("class").beEqualTo(CompactTriHybridBench.class);
        session.let("split").beOneOf(Sample.linear(2, 200, 1));
        final Result run = session.run(2, 8);
        run.showCSV();
    }
}
