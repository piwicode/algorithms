/*
 *  Simple preformance benchmark and algorythms try-out:
 *  https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.stringsort;

import java.util.Arrays;

/**
 *
 * @author plabatut
 */
public class JdkSort extends StringSortBench {

    @Override
    public void run() {
        Arrays.sort(array);
    }

}
