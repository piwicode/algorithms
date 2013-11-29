/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.piwicode.bench.framework.PrettyPrint;
import org.piwicode.bench.framework.Session;

/**
 *
 * @author Pierre
 */
public class MainString {

    public static void main(String[] args) {

        final List<Session> sessions = new ArrayList<>();
        sessions.add(new Session(new CompactTriBench()));
        sessions.add(new Session(new CompactTriHybridBench()));
        sessions.add(new Session(new ObjectTriBench()));
        sessions.add(new Session(new HashSetBench()));
        sessions.add(new Session(new ImmutableHashSetBench()));
        //Warming up
        Session.runAll(sessions, 5);
        //Bench
        Session.runAll(sessions, 50);

        // Summary
        Collections.sort(sessions);
        System.out.println("------------------------------------------------------------------------");
        System.out.println("test presence for " + 80368 + " words of " + (622783 / 80368 - 1) + " chars with 50% matching a value in the set");
        PrettyPrint.printAll(sessions, "-");
    }
}
