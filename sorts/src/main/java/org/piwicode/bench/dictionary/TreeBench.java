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
public class TreeBench extends SetBench {

    @Override
    public void run() {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }
    }

}
