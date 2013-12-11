import java.util.Arrays;
import java.util.Collections;

/*
 * Solution proposal to coursera Algorithms Part 2
 * Programming Assignment 1: WordNet
 */
public class SAP {

    private static final int UNKNOWN = Integer.MAX_VALUE;
    private final Digraph dg;
    private int lastd, lasta;
    private Iterable<Integer> lastvs, lastws;
    private final DFS d1, d2;

    private class DFS {

        private final int queue[];
        private int qh, qt;
        private final int d[];

        private DFS(int size) {
            queue = new int[size];
            d = new int[size];
            Arrays.fill(d, UNKNOWN);
        }

        private void clear() {
            for (int i = 0; i < qt; i++) {
                d[queue[i]] = UNKNOWN;
            }
            qt = qh = 0;
            assert cleanState();
        }

        private boolean cleanState() {
            for (int v : d) {
                if (v != Integer.MAX_VALUE) return false;
            }
            return true;
        }

        private void add(int v, int vdist, DFS other) {
            queue[qt++] = v;
            d[v] = vdist;
            if (other.d[v] != UNKNOWN) {
                final int length = vdist + other.d[v];
                if (lastd > length) {
                    lastd = length;
                    lasta = v;
                }
            }
        }

        private boolean over() {
            return qh == qt;
        }

        private void dfsUpTo(int dmax, DFS other) {
            while (!over() && d[queue[qh]] < dmax) {
                int v = queue[qh++];
                for (int n : dg.adj(v)) {
                    if (d[n] == UNKNOWN) {
                        add(n, d[v] + 1, other);
                    }
                }
            }
        }
    }
    // constructor takes a digraph (not necessarily a DAG)
    // ~V+3*E

    public SAP(Digraph in) {
        d1 = new DFS(in.V());
        d2 = new DFS(in.V());
        dg = new Digraph(in);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    // ~V+E
    public int length(int v, int w) {
        return length(Collections.singletonList(v), Collections.singletonList(w));

    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    // ~V+E
    public int ancestor(int v, int w) {
        return ancestor(Collections.singletonList(v), Collections.singletonList(w));
    }

    /**
     * length of shortest ancestral path between any vertex in v and any vertex
     * in w; -1 if no such path
     *
     * @throws IllegalAccessException is vs or ws elements are out of [0,dg.V()]
     * @throws NullPointerException
     */
    public int length(Iterable<Integer> vs, Iterable<Integer> ws) {
        doubleDfs(vs, ws);
        return lastd;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> vs, Iterable<Integer> ws) {
        doubleDfs(vs, ws);
        return lasta;
    }

    // for unit testing of this class (such as the one below)
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }

    private void checkBound(Iterable<Integer> vs) throws IndexOutOfBoundsException {
        for (int v : vs) {
            if (v >= dg.V() || v < 0) throw new IndexOutOfBoundsException();
        }
    }

    private void doubleDfs(Iterable<Integer> vs, Iterable<Integer> ws) {
        checkBound(vs);
        checkBound(ws);
        if ((vs.equals(lastvs) && ws.equals(lastws))
                || (vs.equals(lastws) && ws.equals(lastvs)))
            return;
        lastvs = vs;
        lastws = ws;
        d1.clear();
        d2.clear();
        lasta = -1;
        lastd = UNKNOWN;
        for (int v : vs) {
            d1.add(v, 0, d2);
        }
        for (int w : ws) {
            d2.add(w, 0, d1);
        }
        for (int dmax = 1; dmax <= lastd; dmax++) {
            d1.dfsUpTo(dmax, d2);
            d2.dfsUpTo(dmax, d1);
            if (d1.over() && d2.over()) break;
        }
        if (lastd == Integer.MAX_VALUE) lastd = -1;
    }
}
