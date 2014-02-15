/*
 *  Simple preformance benchmark and algorythms try-out:
 *  https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.dictionary;

import com.google.common.base.Preconditions;
import java.util.Arrays;

/**
 *
 * @author Pierre
 */
public class IntegerSet {

    private int size;
    private int[] table;
    private double loadFactor = .75;
    private int rehash;

    public IntegerSet() {

        table = new int[256];
        rehash = (int) (table.length * loadFactor);
        Arrays.fill(table, Integer.MIN_VALUE);
        size = 0;
    }

    public boolean add(int value) {
        int p = locate(value);
        if (table[p] == Integer.MIN_VALUE) {
            table[p] = value;
            if (++size == rehash) {
                grow(table.length * 2);
            }
            return true;
        } else if (table[p] == value) {
            return false;
        }
        throw new AssertionError();
    }

    public boolean contains(int value) {
        return table[locate(value)] == value;
    }

    private int locate(int value) {
        Preconditions.checkArgument(value != Integer.MIN_VALUE);
        for (int i = 0; i < table.length; i++) {
            int p = hash(value, i) & (table.length - 1);
            if (table[p] == Integer.MIN_VALUE) {
                return p;
            } else if (table[p] == value) {
                return p;
            }
        }
        throw new AssertionError();
    }

    private int hash(int value, int i) {
        // quadratic probing
        return (value & 0x7FFFFFFF) + 3*i*i;
    }

    private void grow(int newCapacity) {
        int[] prev = table;
        table = new int[newCapacity];
        rehash = (int) (table.length * loadFactor);
        Arrays.fill(table, Integer.MIN_VALUE);
        for (int i = 0; i < prev.length; i++) {
            if (prev[i] != Integer.MIN_VALUE) {
                add(prev[i]);
            }
        }
    }
}
