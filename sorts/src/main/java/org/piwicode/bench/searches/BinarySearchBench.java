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
public class BinarySearchBench implements MacroBench {

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
        int lo = 0, hi = array.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (array[mid] > value) {
                hi = mid - 1;
            } else if (array[mid] < value) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
