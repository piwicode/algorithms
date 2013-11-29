/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trie;

import java.util.Arrays;

/**
 *
 * @author Pierre
 */
public class CompactTriHybrid {

    final int[] array;
    final int X;

    public CompactTriHybrid(int[] array, int X) {
        this.array = array;
        this.X = X;
    }

    boolean contains(CharSequence sequence) {
        int p = 0;
        final int length = sequence.length();
        for (int i = 0; i < length; i++) {
            final int count = array[p] >>> 1;
            final int res = binarySearch(p + 1, p + count, sequence.charAt(i));
            if (res < 0) {
                return false;
            }
            p = array[res + count];
        }
        return (array[p] & 1) != 0;
    }

    private int binarySearch(int low, int high, int key) {
        while (high - low >= X) {
            int mid = (low + high) >>> 1;
            int midVal = array[mid];

            if (midVal < key) {
                low = mid + 1;
            } else if (midVal > key) {
                high = mid - 1;
            } else {
                return mid; // key found
            }
        }
        for (; low <= high; low++) {
            if (array[low] == key) {
                return low;
            }
        }
        return -(low + 1);  // key not found.
    }
}
