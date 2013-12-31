/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.framework;

/**
 *
 * @author Pierre
 */
class Statistics {

    public Statistics(TimeUnit tu) {
        this.tu = tu;
    }
    
    final TimeUnit tu;
    double M = 0., S = 0., C = 0.;

    void reset(){
        M=S=C=0.;
    }
    void collate(double v) {
        double Mprev = M;
        M += (v - M) / (C + 1.);
        S += (v - M) * (v - Mprev);
        C++;
    }

    double mean() {
        return M;
    }

    double var() {
        return S / C;
    }

    double stdev() {
        return Math.sqrt(var());
    }

    /**
     * 95% confidence
     *
     * @return
     */
    double lowerConfBound() {
        return mean() - Math.sqrt(var() / C);
    }

    /**
     * 95% confidence
     *
     * @return
     */
    double upperConfBound() {
        return mean() + Math.sqrt(var() / C);
    }

    String format(double v) {
        return TimeUnit.s.format(v * 1E-3);
    }

    @Override
    public String toString() {
        return "sample size:" + (long) C + " - mean:" + format(mean()) + " - stdev:" + format(stdev()) + " - Ic95%:[" + format(lowerConfBound()) + "," + format(upperConfBound()) + "]";
    }

    void writeTo(Report report) {
        report.write("sample size", (long)C);
        report.write("mean", mean());
        report.write("stdev", stdev());
        report.write("Ic95 upper", upperConfBound());
        report.write("Ic95 lower", lowerConfBound());        
    }

}
