/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sorts;

import java.util.Arrays;

public class NaiveRadixCountingSort extends SortBench {

    int bucket[] = new int[257];
    int buffer[];

    @Override
    public void prepare() {
        super.prepare();
        buffer = new int[N];
    }

    @Override
    public void run() {
        sort(array, buffer, bucket);
    }

    static void sort(int[] array, int[] buffer, int[] bucket) {
        for (int shift = 0; shift < 32;) {
            bucketSort(array, buffer, bucket, shift);
            shift += 8;
            bucketSort(buffer, array, bucket, shift);
            shift += 8;
        }
    }

    private static void bucketSort(int[] array, int[] buffer, int[] bucket, int shift) {
        Arrays.fill(bucket, 0);
        for (int i = 0; i < array.length; i++) {
            final int idx = (array[i] >>> shift) & 0xff;
            bucket[idx + 1]++;
        }
        for (int i = 1; i < bucket.length; i++) {
            bucket[i] += bucket[i - 1];
        }
        for (int i = 0; i < array.length; i++) {
            final int idx = (array[i] >>> shift) & 0xff;
            buffer[bucket[idx]++] = array[i];
        }
    }
}
