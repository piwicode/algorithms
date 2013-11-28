package com.mycompany.sorts;

/**
 * Hello world!
 *
 */
public class Mergesort extends SortBench {

    private int[] buffer;

    @Override
    public void prepare() {
        super.prepare();
        buffer = new int[N];
    }

    @Override
    public void run() {
        sort(array, buffer, 0, N - 1);
    }

    static void sort(int[] array, int[] buffer, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = (lo + hi) >>> 1;
        sort(array, buffer, lo, mid);
        sort(array, buffer, mid + 1, hi);
        System.arraycopy(array, lo, buffer, lo, mid - lo + 1);
        int p = lo, q = mid + 1, r = lo;
        while (p <= mid && q <= hi) {
            if (buffer[p] <= array[q]) {
                array[r++] = buffer[p++];
            } else {
                array[r++] = array[q++];
            }
        }
        while (p <= mid) {
            array[r++] = buffer[p++];
        }
    }
}
