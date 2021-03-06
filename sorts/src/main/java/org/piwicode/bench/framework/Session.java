/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.framework;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Pierre
 */
public class Session {

    ImmutableList.Builder<Set<Entry<String, Object>>> dimensions = ImmutableList.builder();

    public interface Variation {

        void beEqualTo(Object contant);

        void beOneOf(Iterable<?> discreteValues);

        void beOneOf(Object... discreteValues);

        public void beInRange(int i, int i0);
    }

    public Variation let(final String parameterName) {
        return new Variation() {

            @Override
            public void beEqualTo(Object value) {
                dimensions.add(ImmutableSet.of(Maps.immutableEntry(parameterName, value)));
            }

            @Override
            public void beOneOf(Object... discreteValues) {
                beOneOf(Arrays.asList(discreteValues));
            }

            @Override
            public void beOneOf(Iterable<?> discreteValues) {
                final ImmutableSet.Builder<Entry<String, Object>> builder = ImmutableSet.builder();
                for (Object value : discreteValues) {
                    builder.add(Maps.immutableEntry(parameterName, value));
                }
                dimensions.add(builder.build());
            }

            @Override
            public void beInRange(int from, int to) {
                beOneOf(Sample.linear(from, to, 1));
            }
        };
    }

    public static Session create() {
        return new Session();
    }

    public Result run(int warmup, int runs) {
        final List<Experiment> experiments = new ArrayList<>();
        final Set<List<Entry<String, Object>>> cartesianProduct = Sets.cartesianProduct(dimensions.build());
        for (List<Entry<String, Object>> configEntries : cartesianProduct) {
            Configuration config = Configuration.create(configEntries);
            experiments.add(Experiment.create(config));
        }
        long last = 0;
        Collections.shuffle(experiments);
        for (int i = 0; i < warmup; i++) {
            for (Experiment m : experiments) {
                m.run();
                if (System.currentTimeMillis() - last > 1000) {
                    System.out.printf("Warming up [%d/%d]\n", i , warmup);
                    last = System.currentTimeMillis();
                }
            }
        }
        System.out.printf("Wariming up [%d/%d]\n", warmup, warmup);

        for (Experiment m : experiments) {
            m.reset();
        }

        for (int i = 0; i < runs; i++) {
            for (Experiment m : experiments) {
                m.run();
                if (System.currentTimeMillis() - last > 1000) {
                    System.out.printf("Bench [%d/%d]\n", i , runs);
                    last = System.currentTimeMillis();
                }
            }
        }
        System.out.printf("Bench [%d/%d]\n", runs, runs);

        return new Result(experiments);
    }
}
