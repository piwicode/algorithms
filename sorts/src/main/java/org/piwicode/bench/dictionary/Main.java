/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.dictionary;

import java.util.Random;
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
                HashSetBench.class,
                TreeBench.class,
                IntegerSetBench.class,
                BitsetBench.class);

        Result result = session.run(10, 10);
        result.plotBarChart("Average elapsed time to fill with one million integers", "class: Data structure", "mean").show();        
    }
}
