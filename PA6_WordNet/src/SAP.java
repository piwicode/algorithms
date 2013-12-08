import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Solution proposal to coursea Algorithms Part 2
 * Programming Assignment 1: WordNet
 */
public class SAP {

    private final Digraph dg;
    private int lastd, lasta;
    int queue[], qh, qt;
    private final int d[];
    private final byte c[];

    // constructor takes a digraph (not necessarily a DAG)
    // ~V+3*E
    public SAP(Digraph in) {
        this.dg = new Digraph(in);
        d = new int[in.V()];
        queue = new int[in.V() * 2];
        c = new byte[in.V()];
        Arrays.fill(d, Integer.MAX_VALUE);
        Arrays.fill(c, (byte) 0);
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

        for (int i = 0; i < qt; i++) {
            d[queue[i]] = Integer.MAX_VALUE;
            c[queue[i]] = 0;
        }
        assert cleanState();
        qh = 0;
        qt = 0;
        for (int v : vs) {
            queue[qt++] = v;
            d[v] = 0;
            c[v] = 1;
        }
        for (int w : ws) {
            queue[qt++] = w;
            d[w] = 0;
            if (c[w] == 1) {
                lasta = w;
                lastd = 0;
                return;
            }
            c[w] = 2;
        }
        while (qh < qt) {
            int n = queue[qh++];
            for (int to : dg.adj(n)) {
                if (c[to] == 0) {
                    queue[qt++] = to;
                    c[to] = c[n];
                    d[to] = d[n] + 1;
                } else if (c[to] != c[n]) {
                    lasta = to;
                    lastd = d[to] + d[n] + 1;
                    return;
                }
            }
        }
        lasta = -1;
        lastd = -1;
    }

    private boolean cleanState() {
        for (int v : d) {
            if (v != Integer.MAX_VALUE) return false;
        }
        for (byte v : c) {
            if (v != (byte) 0) return false;
        }
        return true;
    }

}
