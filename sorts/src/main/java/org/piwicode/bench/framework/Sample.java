/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.piwicode.bench.framework;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;

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
