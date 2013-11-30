/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package ogr.piwicode.bench.sorts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.piwicode.bench.framework.PrettyPrint;
import org.piwicode.bench.framework.Report;
import org.piwicode.bench.framework.Session;

/**
 *
 * @author Pierre
 */
public class MainHibridOptimize {

    public static void main(String[] args) {
        final HybridQuicksort sort = new HybridQuicksort();
        final List<Session> sessions = new ArrayList<>();
        for (int X = 2; X < 300; X++) {
            sort.setSplit(X);
            sessions.add(new Session(sort).with("split", X));
        }
        Collections.shuffle(sessions);
        Report report = new Report();
        //Warming up
        Session.runAll(sessions, 2);
        //Bench
        Session.runAll(sessions, 2);

        Collections.sort(sessions);
        System.out.println("------------------------------------------------------------------------");
        PrettyPrint.printAll(sessions, "-");
    }
}
