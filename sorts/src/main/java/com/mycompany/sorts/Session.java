/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sorts;

/**
 *
 * @author Pierre
 */
public class Session implements Comparable<Session> {

    private static final int MAX_RETRY = 4;
    private final MacroBench bench;
    private final Statistics stat = new Statistics();
    private final Configuration config = new Configuration();
    private final GCWatch gcWatch = GCWatch.instance();

    public Session(final MacroBench bench) {
        this.bench = bench;
    }

    public Session with(String name, Object value) {
        config.put(name, value);
        return this;
    }

    private long sampleElapsedTime() throws RuntimeException {
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
        config.configure(bench);
        bench.prepare();

        long beforeGc, afterGc, elapsedTime;
        for (int retry = 0; retry < MAX_RETRY; retry++) {
            beforeGc = gcWatch.getGCCount();
            elapsedTime = sampleElapsedTime();
            afterGc = gcWatch.getGCCount();
            if (afterGc == beforeGc) {
                stat.collate(elapsedTime/1000000.);
                System.out.println(this);
                return;
            }
            System.out.println("Discard: " + retry + "/" + MAX_RETRY + " " + name());
        }
        throw new RuntimeException("Garbage collector activity too high");
    }

    public String name() {
        String[] parts = bench.toString().split("\\.");
        return parts[parts.length - 1].split("@")[0];
    }

    @Override
    public String toString() {
        return name() + " - " + stat;
    }
    public void writeTo(Report report){
        report.write("name", name());
        config.writeTo(report);
        stat.writeTo(report);
    }

    public static void runAll(Iterable<Session> sessions, int sampleSize) {
        for (Session session : sessions) {
            session.stat.reset();
        }
        for (int i = 0; i < sampleSize; i++) {
            for (Session session : sessions) {
                session.run();
            }
        }
    }

    @Override
    public int compareTo(Session o) {
        return Double.compare(stat.mean(), o.stat.mean());
    }

}
