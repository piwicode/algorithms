package smr604;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Pierre
 */
public class FoxConnection2 {

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

    static class Vertex {
        final List<Integer> adj = new ArrayList<Integer>();
    }
    private static final long M = 1000000007L;

    public static int ways(int[] A, int[] B, int fox) {
        final Vertex[] v = toAdjacence(A, B);
        final long[][] m = new long[v.length][fox + 1];
        DFS(v, 0, m, fox);
        long total = 0;
        for (long[] row : m) {
            total += row[fox];
        }
        return (int) (total % M);
    }

    private static void DFS(Vertex[] v, int cur, long[][] m, int fox) {
        for (int a : v[cur].adj) {
            v[a].adj.remove((Integer) cur);
            DFS(v, a, m, fox);
        }
        long[] p = new long[fox + 1], r = m[cur];
        r[0] = 1;
        for (int a : v[cur].adj) {
            System.arraycopy(r, 0, p, 0, p.length);
            Arrays.fill(r, 0);
            for (int f = 0; f < fox; f++) {
                for (int l = 0; l <= f; l++) {
                    r[f] = (r[f] + p[l] * m[a][f - l]) % M;
                }
            }
        }
        System.arraycopy(r, 0, m[cur], 1, m[cur].length - 1);        
    }
}
