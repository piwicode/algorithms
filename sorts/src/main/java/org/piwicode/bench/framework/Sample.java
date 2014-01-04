/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.framework;

import com.google.common.collect.ImmutableList;

/**
 *
 * @author Pierre
 */
public class Sample {

    public static Iterable<Integer> linear(int from, int to, int step) {
        final ImmutableList.Builder<Integer> ints = ImmutableList.builder();
        for (int i = from; i < to; i += step) {
            ints.add(i);
        }
        return ints.build();
    }

    public static Iterable<Double> linear(double from, double to, double step) {
        final ImmutableList.Builder<Double> ints = ImmutableList.builder();
        for (double i = from; i < to; i += step) {
            ints.add(i);
        }
        return ints.build();
    }
}
