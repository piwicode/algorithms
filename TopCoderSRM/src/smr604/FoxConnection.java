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

    private static IdxDist dfs(Vertex[] v, boolean[] fox, int c, boolean[] known) {
        known[c] = true;
        int count = 0;
        IdxDist best = null;
        for (int a : v[c].adj) {
            if (known[a]) {
                count++;
                continue;
            }
            IdxDist res = dfs(v, fox, a, known);
            if (res == null) continue;
            count++;
            if (best == null || res.d < best.d) best = res;
        }
        if (count == 1) best = fox[c] ? new IdxDist(c, 0) : null;
        else if (best != null) best.d++;
        return best;
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

    public static int minimalDistance(int[] A, int[] B, String F) {
        final Vertex[] v = toAdjacence(A, B);
        final boolean[] fox = toBooleans(F);
        final boolean[] known = new boolean[v.length];
        int sum = 0;
        while (true) {
            IdxDist best = null;
            int hole = -1;
            for (int i = 0; i < v.length; i++) {
                if (fox[i]) continue;
                Arrays.fill(known, false);
                IdxDist res = dfs(v, fox, i, known);
                if (res == null) continue;
                if (best == null || best.d > res.d) {
                    best = res;
                    hole = i;
                }
            }
            if (best == null) break;
            fox[hole] = true;
            fox[best.v] = false;
            sum += best.d;
        }
        return sum;
    }
}
