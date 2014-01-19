package smr604;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Pierre
 */
public class FoxConnection {

    static Vertex[] toAdjacence(int[] A, int[] B) {
        final Vertex[] v = new Vertex[A.length + 1];
        for (int i = 0; i < v.length; i++) {
            v[i] = new Vertex();
        }
        for (int i = 0; i < A.length; i++) {
            v[A[i] - 1].adj.add(B[i] - 1);
            v[B[i] - 1].adj.add(A[i] - 1);
        }
        return v;
    }

    private static boolean[] toBooleans(String F) {
        final boolean[] fox = new boolean[F.length()];
        for (int i = 0; i < F.length(); i++) {
            fox[i] = F.charAt(i) == 'Y';
        }
        return fox;
    }

    private void countAndRootDFS(int c) {
        foxCount[c] = fox[c] ? 1 : 0;
        for (int a : v[c].adj) {
            v[a].adj.remove((Integer) c);
            countAndRootDFS(a);
            foxCount[c] += foxCount[a];
        }
    }

    private void bestDFS(int x) {
        //best[x] is the minimum number of move to connect foxes in subtree of root x, assuming the foxes out
        //of the subtree have already been moved to x
        best[x] = theCost(x, 0, foxCount[0]);// x is the best sub tree
        // Now let's give a chance to solution made of a children subtree
        for (int a : v[x].adj) {
            bestDFS(a);
            double acost = best[a]; // compose solution as subtree a 
            acost += foxCount[0] - foxCount[x]; // foxes out of x subtree are stored in x (see assumption) then goes x->a
            acost += fox[x] ? 1 : 0; // the fox in x (if any) goes x->a
            for (int o : v[x].adj) {
                if (o == a) continue;
                acost += theCost(o, 0, 0);//cost of clearing the subgraph o, homeless foxes stored in o.
                acost += foxCount[o] * 2;// the homeless foxes goes o->x->a
            }
            best[x] = Math.min(best[x], acost);
        }
    }

    /**
     * Cost of creating a connected component of k fox in subtree x.
     * The connected component include x if k!=0.
     * It assume that missing or superfluity foxes have
     * come to x.
     *
     * @param x subtree root
     * @param e number of children to ignore
     * @param k number of fox to install
     * @return +INF if not possible, the cost otherwize
     */
    private double theCost(int x, int e, int k) {
        if (m[x][e][k] < 0) m[x][e][k] = doTheCost(x, e, k);
        return m[x][e][k];
    }

    private double doTheCost(int x, int e, int k) {
        if (e == v[x].adj.size())
            //Can't use any children to compos this component. 
            //Fox capacity is 1.
            //0 because the fox or notfox is already in k (see assumption)
            return k > 1 ? Double.POSITIVE_INFINITY : 0;

        double smallest = Double.POSITIVE_INFINITY;
            // Maximum number of fox that can be allocated to the child
        // A fox is placed at the root. Special case for empty tree.            
        int maxFox = Math.max(k - 1, 0);
        //Try number of fox for children of index e
        int child = v[x].adj.get(e);
        for (int foxInChildE = 0; foxInChildE <= maxFox; foxInChildE++) {
            //Add the cost of constituting the component in child
            double cost = theCost(child, 0, foxInChildE);
                //Add the cost of additional fox moving x->child
            //or cost of superfluity foxes moving child->x
            cost += Math.abs(foxCount[child] - foxInChildE);
            //Add the cost of creating the layout of remaining foxes
            cost += theCost(x, e + 1, k - foxInChildE);
            smallest = Math.min(cost, smallest);
        }
        return smallest;
    }

    static class Vertex {
        final List<Integer> adj = new ArrayList<Integer>();
    }

    static class IdxDist {
        int v, d;

        public IdxDist(int v, int d) {
            this.v = v;
            this.d = d;
        }
    }
    final Vertex[] v;
    final boolean[] fox;
    final int[] foxCount;
    final double[] best;
    final double m[][][];

    public FoxConnection(int[] A, int[] B, String F) {
        v = toAdjacence(A, B);
        fox = toBooleans(F);
        foxCount = new int[v.length];
        countAndRootDFS(0);
        best = new double[v.length];
        m = new double[v.length][v.length][foxCount[0]+1];
        for (double[][] i : m) {
            for (double[] j : i) {
                Arrays.fill(j, -1);
            }
        }
    }

    private int minimalDistance() {        
        bestDFS(0);
        return (int) best[0];
    }

    public static int minimalDistance(int[] A, int[] B, String F) {
        return new FoxConnection(A, B, F).minimalDistance();
    }
}
