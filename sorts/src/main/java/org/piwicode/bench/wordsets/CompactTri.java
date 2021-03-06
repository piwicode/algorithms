/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.wordsets;

import java.util.Arrays;

/**
 *
 * @author Pierre
 */
public class CompactTri {

    final int[] array;

    public CompactTri(int[] array) {
        this.array = array;
    }

    boolean contains(CharSequence sequence) {
        int p = 0;
        final int length = sequence.length();
        for (int i = 0; i < length; i++) {
            final int count = array[p] >>> 1;
            final int res = Arrays.binarySearch(array, p + 1, p + count + 1,  sequence.charAt(i));
            if (res < 0) return false;
            p = array[res + count];
        }
        return (array[p] & 1) != 0;
    }

}
