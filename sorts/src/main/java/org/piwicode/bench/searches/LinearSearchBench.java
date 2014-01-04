/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.searches;

import java.util.Random;
import org.piwicode.bench.framework.IntegerArray;
import org.piwicode.bench.framework.MacroBench;

/**
 *
 * @author Pierre
 */
public class LinearSearchBench implements MacroBench {

    private int array[];
    private int N, R = 100;
    private int value;

    public void setN(int N) {
        this.N = N;
    }

    public void setR(int R) {
        this.R = R;
    }

    @Override
    public void prepare() {
        array = IntegerArray.shuffeledIdentity(N);
        value = new Random().nextInt(N);
    }

    @Override
    public void run() {
        for (int r = 0; r < R; r++) {
            search(array, value);
        }
    }

    static int search(int array[], final int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) return i;
        }
        return -1;
    }
}
