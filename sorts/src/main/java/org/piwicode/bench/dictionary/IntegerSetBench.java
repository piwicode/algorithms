/*
 *  Simple preformance benchmark and algorythms try-out:
 *  https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.dictionary;

import java.util.TreeSet;

/**
 *
 * @author Pierre
 */
public class IntegerSetBench extends SetBench {

    @Override
    public void run() {
        IntegerSet set = new IntegerSet();
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }
    }

}
