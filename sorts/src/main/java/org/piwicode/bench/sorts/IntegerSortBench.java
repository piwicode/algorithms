/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

import org.piwicode.bench.framework.MacroBench;
import org.piwicode.bench.framework.IntegerArray;


public abstract class IntegerSortBench implements MacroBench {

    int[] array;
    int N = 1000 * 1000;
    double P = 0.;

    public void setN(int N) {
        this.N = N;
    }

    public void setP(double P) {
        this.P = P;
    }

    @Override
    public void prepare() {
        array = new int[N];
        assign(array, P);
        IntegerArray.shuffle(array);
    }

    static void assign(int[] array, double P) {
        int M = (int) ((1. - P) * array.length + 1);
        for (int i = 0; i < array.length; i++) {
            array[i] = i % M;
        }
    }

}
