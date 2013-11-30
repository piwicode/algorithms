/*
 * Solution proposal to coursea Algorithms Part 2
 * Programming Assignment 1: WordNet
 */

public class SAP {

    private Digraph dg;
    private int halfSize, bfsId = -1;
    private BreadthFirstDirectedPaths bfs;

    // constructor takes a digraph (not necessarily a DAG)
    // ~V+3*E
    public SAP(Digraph dg) {
        halfSize = dg.V();
        dg = new Digraph(halfSize * 2);
        //Fold the graph on itself       
        for (int i = 0; i < halfSize; i++) {
            for (int j : dg.adj(i)) {
                dg.addEdge(i, j);
                dg.addEdge(j + halfSize, i + halfSize);
            }
            //Connect the graph and the reversed grapg
            dg.addEdge(i, i + halfSize);
        }
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    // ~V+E
    public int length(int v, int w) {
        if (w == bfsId) {
            w = v;
            v = bfsId;
        }
        if (v != bfsId) {
            bfsId = v;
            bfs = new BreadthFirstDirectedPaths(dg, v);
        }
        final int distTo = bfs.distTo(w + halfSize);
        return distTo == Integer.MAX_VALUE ? -1 : distTo;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    // ~V+E
    public int ancestor(int v, int w) {
        if (w == bfsId) {
            w = v;
            v = bfsId;
        }
        if (v != bfsId) {
            bfsId = v;
            bfs = new BreadthFirstDirectedPaths(dg, v);
        }
        Iterable<Integer> path = bfs.pathTo(w + halfSize);
        if (path == null) return -1;
        for (int node : path) {
            if (node > halfSize)
                return node - halfSize;
        }
        throw new RuntimeException("should never happend");
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> vs, Iterable<Integer> ws) {
        final BreadthFirstDirectedPaths mbfs = new BreadthFirstDirectedPaths(dg, vs);
        int distTo = Integer.MAX_VALUE;
        for (int w : ws) {
            distTo = Math.min(distTo, mbfs.distTo(w));
        }
        return distTo == Integer.MAX_VALUE ? -1 : distTo;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> vs, Iterable<Integer> ws) {
        final BreadthFirstDirectedPaths mbfs = new BreadthFirstDirectedPaths(dg, vs);
        int to = -1, min = Integer.MAX_VALUE;
        for (int w : ws) {
            if (mbfs.distTo(w) < min) {
                to = w;
                min = mbfs.distTo(w);
            }
        }

        if (to == -1) return -1;
        for (int node : mbfs.pathTo(to)) {
            if (node > halfSize)
                return node - halfSize;
        }
        throw new RuntimeException("should never happend");
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
}
