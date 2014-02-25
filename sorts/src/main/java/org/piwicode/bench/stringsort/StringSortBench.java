/*
 *  Simple preformance benchmark and algorythms try-out:
 *  https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.stringsort;

import org.piwicode.bench.framework.MacroBench;
import org.piwicode.bench.wordsets.Dictionary;

/**
 *
 * @author plabatut
 */
public abstract class StringSortBench implements MacroBench {

    String[] array;
    int N = 1000 * 1000;

    public void setN(int N) {
        this.N = N;
    }

    @Override
    public void prepare() {
        array = Dictionary.sample(N);
    }

}
