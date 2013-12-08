/*
 * Solution proposal to coursea Algorithms Part 2
 * Programming Assignment 1: WordNet
 */

public class SAP {

    private final Digraph dg;
    private final int halfSize;
    private int bfsId = -1;
    private BreadthFirstDirectedPaths bfs;

    // constructor takes a digraph (not necessarily a DAG)
    // ~V+3*E
    public SAP(Digraph in) {
        halfSize = in.V();
        this.dg = new Digraph(halfSize * 2);
        //Fold the graph on itself       
        for (int i = 0; i < halfSize; i++) {
            for (int j : in.adj(i)) {
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
        checkInputBound(v);
        checkInputBound(w);

        // Check the cache
        if (w == bfsId) {
            w = v;
            v = bfsId;
        }
        if (v != bfsId) {
            bfsId = v;
            bfs = new BreadthFirstDirectedPaths(dg, v);
        }
        final int distTo = bfs.distTo(w + halfSize);
        /*
         Iterable<Integer> pathTo = bfs.pathTo(w + halfSize);
         for (int i : pathTo) {
         if (i != v) System.out.print("->");
         System.out.print(i);
         }
         System.out.println();
         */
        //Removes 1 to the length to ignore the edge connecting the graph and its mirror image
        return distTo == Integer.MAX_VALUE ? -1 : distTo - 1;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    // ~V+E
    public int ancestor(int v, int w) {
        checkInputBound(v);
        checkInputBound(w);
        //Check the cache
        if (w == bfsId) {
            w = v;
            v = bfsId;
        }
        if (v != bfsId) {
            bfsId = v;
            bfs = new BreadthFirstDirectedPaths(dg, v);
        }
        return ancestorOfPath(bfs.pathTo(w + halfSize));
    }

    /**
     * length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
     * @throws IllegalAccessException is vs or ws elements are out of [0,dg.V()]
     * @throws NullPointerException 
     */ 
    public int length(final Iterable<Integer> vs, final Iterable<Integer> ws) {
        checkBound(vs);
        checkBound(ws);
        final BreadthFirstDirectedPaths mbfs = new BreadthFirstDirectedPaths(dg, vs);        
        final int to = closestFrom(ws, mbfs);
        if (to == -1) return -1;
        final int distTo = mbfs.distTo(to+halfSize);
        return distTo == Integer.MAX_VALUE ? -1 : distTo - 1;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> vs, Iterable<Integer> ws) {
        checkBound(vs);
        checkBound(ws);
        final BreadthFirstDirectedPaths mbfs = new BreadthFirstDirectedPaths(dg, vs);
        int to = closestFrom(ws, mbfs);
        if (to == -1) return -1;
        return ancestorOfPath(mbfs.pathTo(to + halfSize));
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

    private void checkInputBound(int v) throws IndexOutOfBoundsException  {
        if (v >= halfSize || v < 0) throw new IndexOutOfBoundsException ();
    }

    private void checkBound(Iterable<Integer> vs) throws IndexOutOfBoundsException  {
        for (int v : vs) {
            checkInputBound(v);
        }
    }

    private int ancestorOfPath(Iterable<Integer> path) throws RuntimeException {
        if (path == null) return -1;
        for (int node : path) {
            if (node >= halfSize)
                return node - halfSize;
        }
        throw new RuntimeException("should never happend");
    }

    private int closestFrom(Iterable<Integer> ws, final BreadthFirstDirectedPaths mbfs) {
        int to = -1, min = Integer.MAX_VALUE;
        for (int w : ws) {
            final int d = mbfs.distTo(w + halfSize);
            if (d < min) {
                to = w;
                min = d;
            }
        }
        return to;
    }
}
