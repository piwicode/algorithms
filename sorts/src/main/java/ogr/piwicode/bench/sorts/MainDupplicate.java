/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package ogr.piwicode.bench.sorts;

import java.util.ArrayList;
import java.util.List;
import org.piwicode.bench.framework.MacroBench;
import org.piwicode.bench.framework.Report;
import org.piwicode.bench.framework.Session;

/**
 *
 * @author Pierre
 */
public class MainDupplicate {

    public static void main(String[] args) {
        MacroBench[] benches = {new Quicksort(),
            new QuicksortWithTailRecursion(),
            new Mergesort(),
            new DualpivotQuicksort(),
            new Heapsort(),
            new HybridQuicksort(),
            new RadixCountingSort(),
            new RadixBinarySort()};
        final List<Session> sessions = new ArrayList<>();
        for (MacroBench bench : benches) {
            for (double p = 0.; p < 1.; p+=.1) {
                sessions.add(new Session(bench).with("p", p));
            }
        }
        
        //Warming up
        Session.runAll(sessions, 1);
        //Bench
        Session.runAll(sessions, 5);

        // Summary        
        System.out.println("------------------------------------------------------------------------");
        Report.writeAllAsCsv(sessions);
    }
}
