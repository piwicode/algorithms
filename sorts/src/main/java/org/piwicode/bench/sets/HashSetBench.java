/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sets;

import java.util.HashSet;

/**
 *
 * @author Pierre
 */
class HashSetBench extends SetBench {

    HashSet<String> set;

    @Override
    public void prepare() {
        super.prepare();         
        set = new HashSet<>(words);
    }

    @Override
    public void run() {
        for (String w : candidates) {
            set.contains(w);
        }
    }

}
