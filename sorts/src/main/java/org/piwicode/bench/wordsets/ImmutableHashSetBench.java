/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.wordsets;

import com.google.common.collect.ImmutableSet;

/**
 *
 * @author Pierre
 */
class ImmutableHashSetBench extends SetBench {

    ImmutableSet<String> set;

    @Override
    public void prepare() {
        super.prepare(); 
        set=ImmutableSet.copyOf(words);
    }

    @Override
    public void run() {
        for (String w : candidates) {
            set.contains(w);
        }
    }

}
