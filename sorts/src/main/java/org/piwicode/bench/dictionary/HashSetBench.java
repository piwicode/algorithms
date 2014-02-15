/*
 *  Simple preformance benchmark and algorythms try-out:
 *  https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.dictionary;

import java.util.HashSet;
import java.util.Random;
import org.piwicode.bench.framework.MacroBench;

/**
 *
 * @author Pierre
 */
public class HashSetBench extends SetBench {

    @Override
    public void run() {
        final HashSet<Integer> set = new HashSet<>((int) (N / .75));
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }
    }

}
