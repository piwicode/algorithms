/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.piwicode.bench.framework.Report;
import org.piwicode.bench.framework.Session;

/**
 *
 * @author Pierre
 */
public class MainCompactHybridSplit {

    public static void main(String[] args) {
        final CompactTriHybridBench bench = new CompactTriHybridBench();
        final List<Session> sessions = new ArrayList<>();
        for (int X = 0; X < 200; X += 1) {
            sessions.add(new Session(bench).with("split", X));
        }
        Collections.shuffle(sessions);
        //Warming up
        Session.runAll(sessions, 2);
        //Bench
        Session.runAll(sessions, 8);

        Collections.sort(sessions);
        System.out.println("------------------------------------------------------------------------");
        Report.writeAllAsCsv(sessions);
    }
}
