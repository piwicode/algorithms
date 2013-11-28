package com.mycompany.sorts;

/**
 * Hello world!
 *
 */
public class Heapsort extends SortBench {

    @Override
    public void run() {
        sort(array);
    }

    static void sort(int[] array) {
        for (int i = array.length >> 1; i >= 0; i--) {
            bubbleDown(array, array.length, i);
        }
        for (int i = array.length - 1; i > 0; i--) {
            int t = array[0];
            array[0] = array[i];
            array[i] = t;
            bubbleDown(array, i, 0);
        }
    }

    static void bubbleDown(int[] array, int length, int i) {
        int child = i << 1, max = i;
        if (child < length && array[child] > array[max]) {
            max = child;
        }
        child++;
        if (child < length && array[child] > array[max]) {
            max = child;
        }
        if (max == i) {
            return;
        }
        int t = array[i];
        array[i] = array[max];
        array[max] = t;
        bubbleDown(array, length, max);
    }
}
