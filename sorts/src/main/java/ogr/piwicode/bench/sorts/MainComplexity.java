/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package ogr.piwicode.bench.sorts;

import org.piwicode.bench.framework.Report;
import org.piwicode.bench.framework.MacroBench;
import org.piwicode.bench.framework.Session;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pierre
 */
public class MainComplexity {

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
            for (int N =  10; N <1000; N += 10) {
                sessions.add(new Session(bench).with("n", N));
            }
        }
        
        //Warming up
        long s = System.currentTimeMillis()+1000*20;
        while(System.currentTimeMillis()<s)
            Session.runAll(sessions, 1);
        //Bench 
        Session.runAll(sessions, 200);

        // Summary        
        System.out.println("------------------------------------------------------------------------");
        Report.writeAllAsCsv(sessions);
    }
}
