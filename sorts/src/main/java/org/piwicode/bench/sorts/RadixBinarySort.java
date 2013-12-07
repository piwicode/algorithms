/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

public class RadixBinarySort extends SortBench {

    int buffer[];

    @Override
    public void prepare() {
        super.prepare();
        buffer = new int[N];
    }

    @Override
    public void run() {
        sort(array, buffer);
    }

    static void sort(int[] array, int[] buffer) {
        for (int shift = 0; shift != 64; shift++) {
            int p = 0, q = 0, m = 1 << shift;
            for (int i = 0; i < array.length; i++) {
                final int v = array[i];
                if ((v & m) != 0) {
                    buffer[q++] = v;
                } else {
                    array[p++] = v;
                }

            }
            System.arraycopy(buffer, 0, array, p, q);
        }
    }
}
