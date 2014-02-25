/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

import java.util.Arrays;

public class RadixCountingSort extends IntegerSortBench {

    int bucket[] = new int[257];
    int bucket2[] = new int[257];
    int buffer[];

    @Override
    public void prepare() {
        super.prepare();
        buffer = new int[N];
    }

    @Override
    public void run() {
        sort(array, buffer, bucket, bucket2);
    }

    static void sort(int[] array, int[] buffer, int[] bucket, int[] bucket2) {
        Arrays.fill(bucket, 0);
        for (int i = 0; i < array.length; i++) {
            bucket[(array[i] & 0xff) + 1]++;
        }
        for (int shift = 0; shift < 24;) {
            int nextShift = shift + 8;
            for (int i = 1; i < bucket.length; i++) {
                bucket[i] += bucket[i - 1];
            }
            Arrays.fill(bucket2, 0);
            for (int i = 0; i < array.length; i++) {
                final int v = array[i];
                bucket2[((v >>> nextShift) & 0xff) + 1]++;
                buffer[bucket[(v >>> shift) & 0xff]++] = array[i];
            }
            int[] ta = array;
            array = buffer;
            buffer = ta;
            int[] tb = bucket;
            bucket = bucket2;
            bucket2 = tb;
            shift = nextShift;
        }
        for (int i = 0; i < array.length; i++) {
            final int idx = (array[i] >>> 24) & 0xff;
            buffer[bucket[idx]++] = array[i];
        }
    }

}
