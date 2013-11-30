/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package ogr.piwicode.bench.sorts;

import org.piwicode.bench.framework.PrettyPrint;
import org.piwicode.bench.framework.Session;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Pierre
 */
public class Main {

    public static void main(String[] args) {

        final List<Session> sessions = new ArrayList<>();
        sessions.add(new Session(new Quicksort()));
        sessions.add(new Session(new QuicksortWithTailRecursion()));
        sessions.add(new Session(new Mergesort()));
        sessions.add(new Session(new DualpivotQuicksort()));
        sessions.add(new Session(new Heapsort()));
        sessions.add(new Session(new HybridQuicksort()));
        sessions.add(new Session(new RadixCountingSort()));
        sessions.add(new Session(new RadixBinarySort()));
        
        //Warming up
        Session.runAll(sessions, 5);
        //Bench
        Session.runAll(sessions, 20);

        // Summary
        Collections.sort(sessions);
        System.out.println("------------------------------------------------------------------------");
        PrettyPrint.printAll(sessions, "-");
    }
}
