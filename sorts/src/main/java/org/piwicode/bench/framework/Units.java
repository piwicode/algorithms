/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.framework;

import java.text.DecimalFormat;

/**
 *
 * @author Pierre
 */
class Units {

    static String format(double v, Unit unit) {
        final Unit[] scale = (Unit[]) unit.getClass().getEnumConstants();
        Unit d = scale[0];
        v *= unit.ratio();
        for (int i = 0; i < scale.length - 1; i++) {
            d = scale[i];
            if (v < scale[i + 1].ratio()) {
                break;
            }
        }
        return new DecimalFormat("#.##").format(v / d.ratio())+" "+d;
    }
}
