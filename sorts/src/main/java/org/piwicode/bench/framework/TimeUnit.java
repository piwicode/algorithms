/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.framework;

/**
 *
 * @author Pierre
 */
enum TimeUnit implements Unit {
    ns,µs,ms,s,min,h;    
    private static final double[] scale={1E-9,1E-6,1E-3,1E0,60,3600};
    public double ratio() {
        return scale[ordinal()];
    }    
    public static String format(double v){
        return Units.format(v, TimeUnit.class);
    }
}
