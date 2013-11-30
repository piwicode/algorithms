/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.framework;

/**
 *
 * @author Pierre
 */
public class Units {

    static String format(double v, Class<? extends Enum<? extends Unit>> unit) {
        final Unit[] scale= (Unit[]) unit.getEnumConstants();
        Unit d = scale[0];
        for (int i = 0; i < scale.length - 1; i++) {
            d = scale[i];
            if (v < scale[i + 1].ratio()) {
                break;
            }
        }
        return String.format("%.2f " + d, v / d.ratio());
    }
}
