/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

public class Count extends IntegerSortBench {

    int N = 1024 * 1024 * 1024;
    public int r;

    @Override
    public void run() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if ((i & 2) != 0)
                sum += i;
        }
        r = sum;
    }

}
