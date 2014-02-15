
import java.util.Arrays;
import java.util.Random;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/*
 * Solution proposal to TopCoder Single Round Match
 */
/**
 *
 * @author Pierre
 */
public class TestShortPath {
    @Test
    public void test() {
        final EdgeWeightedDigraph g = new EdgeWeightedDigraph(60, 60 * 60);
        DijkstraSP ref = new DijkstraSP(g, 0);
        SPShortestPath hdij = SPShortestPath.heapstra(g, 0);
        System.out.println("hdijkstra " + hdij);
        SPShortestPath dij = SPShortestPath.dijkstra(g, 0);
        System.out.println("dijkstra  " + dij);
        SPShortestPath floyd = SPShortestPath.floydWarshal(g, 0);
        System.out.println("floyd     " + floyd);
        SPShortestPath ford = SPShortestPath.fordFulkerson(g, 0);
        System.out.println("ford      " + ford);
        for (int v = 0; v < g.V(); v++) {
            assertEquals("hdijkasta to " + v, ref.distTo(v), hdij.distTo(v), .0001);
            assertEquals("dijkasta to " + v, ref.distTo(v), dij.distTo(v), .0001);
            assertEquals("floyd to " + v, ref.distTo(v), floyd.distTo(v), .0001);
            assertEquals("ford to " + v, ref.distTo(v), ford.distTo(v), .0001);
        }
    }

    @Test
    public void testHeap() {
        Random rnd = new Random();
        int size=10;
        SPShortestPath.IndexedHeap heap = new SPShortestPath.IndexedHeap(size);
        for (int i = 0; i < size; i++) {
            heap.add(i, rnd.nextDouble());
        }
        heap.show();
        double prev = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < size; i++) {
            int m= heap.removeMin();
            double cur =heap.valueOf(m);
            System.out.println(cur);
            assertTrue(cur >= prev);
            prev = cur;
        }
    }
}
