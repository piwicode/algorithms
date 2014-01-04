/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.framework;

import com.google.common.base.Preconditions;

/**
 *
 * @author Pierre
 */
class Experiment implements Comparable<Experiment> {

    private static final int MAX_RETRY = 4;
    private final Statistics stat = new Statistics(TimeUnit.ns);
    private final Configuration config;
    private final GCWatch gcWatch = GCWatch.instance();
    private final MacroBench bench;

    public static Experiment create(Configuration config) {
        MacroBench bench = (MacroBench) config.spawn();
        return new Experiment(config, bench);
    }

    private Experiment(Configuration config, MacroBench bench) {
        this.config = Preconditions.checkNotNull(config);
        this.bench = Preconditions.checkNotNull(bench);
    }

    private long sampleElapsedTime(MacroBench bench) throws RuntimeException {
        try {
            long before = System.nanoTime();
            bench.run();
            long after = System.nanoTime();
            return after - before;
        } catch (Exception ex) {
            throw new RuntimeException("The benched failed", ex);
        }
    }

    public void run() {
        bench.prepare();

        long beforeGc, afterGc, elapsedTime;
        for (int retry = 0; retry < MAX_RETRY; retry++) {
            beforeGc = gcWatch.getGCCount();
            elapsedTime = sampleElapsedTime(bench);
            afterGc = gcWatch.getGCCount();
            if (afterGc == beforeGc) {
                stat.collate(elapsedTime);                
                return;
            }
            System.out.println("Discard: " + retry + "/" + MAX_RETRY + " " + name());
        }
        throw new RuntimeException("Garbage collector activity too high");
    }

    private String name() {
        String[] parts = bench.toString().split("\\.");
        return parts[parts.length - 1].split("@")[0];
    }

    @Override
    public String toString() {
        return config + " - " + stat;
    }

    public void writeTo(Report report) {
        report.write("name", name());
        config.writeTo(report);
        stat.writeTo(report);
    }

    void reset() {
        stat.reset();
    }

    @Override
    public int compareTo(Experiment o) {
        return Double.compare(stat.mean(), o.stat.mean());
    }

}
