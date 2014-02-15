
import java.util.Arrays;

/*
 * Solution proposal to TopCoder Single Round Match
 */
/**
 *
 * @author Pierre
 */
class SPShortestPath {

    private final double[] d;
    private final int[] parent;

    private SPShortestPath(double[] d, int[] parent) {
        this.d = d;
        this.parent = parent;
    }

    double distTo(int v) {
        return d[v];
    }

    @Override
    public String toString() {
        return Arrays.toString(d);
    }

    public static SPShortestPath fordFulkerson(EdgeWeightedDigraph g, int start) {
        double[] d = new double[g.V()];
        int[] parent = new int[g.V()];
        Arrays.fill(d, Double.POSITIVE_INFINITY);
        d[start] = 0;
        for (int v = 0; v < g.V(); v++) {
            for (DirectedEdge e : g.edges()) {
                if (d[e.from()] + e.weight() < d[e.to()]) {
                    parent[e.to()] = e.from();
                    d[e.to()] = d[e.from()] + e.weight();
                }
            }
        }
        return new SPShortestPath(d, parent);
    }

    public static SPShortestPath floydWarshal(EdgeWeightedDigraph g, int start) {
        double m[][] = new double[g.V()][g.V()];
        for (double[] raw : m) {
            Arrays.fill(raw, Double.POSITIVE_INFINITY);
        }
        for (int v = 0; v < g.V(); v++) {
            m[v][v] = 0.;
        }
        for (DirectedEdge e : g.edges()) {
            //Attention à ne pas se faire avoir par des edges de v->v ou des edges multiples de v->v'
            m[e.from()][e.to()] = Math.min(m[e.from()][e.to()], e.weight());
        }

        for (int k = 0; k < g.V(); k++) {
            for (int i = 0; i < g.V(); i++) {
                for (int j = 0; j < g.V(); j++) {
                    m[i][j] = Math.min(m[i][j], m[i][k] + m[k][j]);
                }
            }
        }
        return new SPShortestPath(m[start], null);
    }

    public static SPShortestPath dijkstra(EdgeWeightedDigraph g, int start) {
        double[] d = new double[g.V()];
        Arrays.fill(d, Double.POSITIVE_INFINITY);
        int[] parent = new int[g.V()];
        boolean[] done = new boolean[g.V()];
        d[start] = 0;
        for (int i = 0; i < g.V(); i++) {
            int closest = -1;
            for (int v = 0; v < g.V(); v++) {
                if (!done[v] && (closest == -1 || d[v] < d[closest]))
                    closest = v;
            }
            if (closest == -1 || d[closest] == Double.POSITIVE_INFINITY) break;
            done[closest] = true;
            for (DirectedEdge de : g.adj(closest)) {
                double candidate = d[closest] + de.weight();
                if (candidate < d[de.to()]) {
                    d[de.to()] = candidate;
                    parent[de.to()] = closest;
                }
            }
        }
        return new SPShortestPath(d, parent);
    }

    static class IndexedHeap {
        int size = 0;
        double[] value; // distance by vertex index
        int[] pos; //position in the heay by vertex index
        int[] heap; // vertex index by position in heap

        IndexedHeap(int capacity) {
            value = new double[capacity];
            pos = new int[capacity];
            Arrays.fill(pos, -1);
            heap = new int[capacity];
        }

        void decrease(int vertex, double d) {
            if (pos[vertex] == -1) {
                System.out.println("Vertex " + vertex + " is not in heap value:" + value[vertex] + " d:" + d);
                return;
            }
            if (value[vertex] < d)
                throw new IllegalArgumentException();

            value[vertex] = d;
            siftUp(pos[vertex]);
            isHeap();
        }

        void add(int vertex, double d) {
            value[vertex] = d;
            pos[vertex] = size;
            heap[size] = vertex;
            size++;
            siftUp(pos[vertex]);
            isHeap();
        }

        void siftUp(int current) {
            while (current > 0) {
                int parent = (current - 1) / 2;
                if (value[heap[parent]] <= value[heap[current]]) break;
                int tmp = heap[parent];
                heap[parent] = heap[current];
                heap[current] = tmp;
                pos[heap[parent]] = parent;
                pos[heap[current]] = current;
                current = parent;
            }
        }

        int removeMin() {
            if (size == 0) throw new IllegalStateException();
            int result = heap[0];
            pos[heap[0]] = -1;
            size--;
            if (size > 0) {
                heap[0] = heap[size];
                pos[heap[0]] = 0;
                siftDown(0);
            }
            isHeap();
            return result;
        }

        void isHeap() {
            for (int i = 1; i < size; i++) {
                int parent = (i - 1) / 2;
                if (value[heap[parent]] > value[heap[i]]) throw new AssertionError();
            }
            
        }
        void siftDown(int heapIdx) {

            do {
                int lc = (heapIdx + 1) * 2-1, min = heapIdx;
                if (lc < size && value[heap[lc]] < value[heap[min]]) min = lc;
                if (lc + 1 < size && value[heap[lc + 1]] < value[heap[min]]) min = lc + 1;
                if (min == heapIdx) break;
                int t = heap[min];
                heap[min] = heap[heapIdx];
                heap[heapIdx] = t;
                pos[heap[min]] = min;
                pos[heap[heapIdx]] = heapIdx;
                heapIdx = min;
            } while (true);
        }

        double valueOf(int v) {
            return value[v];
        }

        void show() {
            StringBuilder sb = new StringBuilder();

            for (int i = 1, cur = 0; cur < size; i *= 2) {

                for (int r = 0; r < i && cur < size; r++) {
                    System.out.printf("%.3f ", value[heap[cur++]]);

                }

                System.out.println();
            }
        }
    }

    public static int log2(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        return 31 - Integer.numberOfLeadingZeros(n);
    }

    public static SPShortestPath heapstra(EdgeWeightedDigraph g, int start) {
        IndexedHeap heap = new IndexedHeap(g.V());
        int[] parent = new int[g.V()];
        for (int v = 0; v < g.V(); v++) {
            heap.add(v, v == start ? 0 : Double.POSITIVE_INFINITY);
        }

        for (int i = 0; i < g.V(); i++) {
            int closest = heap.removeMin();
            System.out.println("Remove " + closest + " with dist " + heap.valueOf(closest));
            if (heap.valueOf(closest) == Double.POSITIVE_INFINITY) break;
            for (DirectedEdge de : g.adj(closest)) {
                double newDist = heap.valueOf(closest) + de.weight();
                if (heap.valueOf(de.to()) > newDist) {
                    heap.decrease(de.to(), newDist);
                    parent[de.to()] = closest;
                }
            }
        }
        return new SPShortestPath(heap.value, parent);
    }
}
