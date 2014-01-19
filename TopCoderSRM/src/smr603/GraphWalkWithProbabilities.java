/*
 * Solution proposal to TopCoder Single Round Match
 */
package smr603;

/**
 *
 * @author Pierre
 */
public class GraphWalkWithProbabilities {

    static boolean adj(String[] graph, int i, int j) {
        return graph[i].charAt(j) == '1';
    }

    public static double findprob(String[] graph, int[] winprob, int[] looseprob, int start) {

        // pw[start of the path][length of the path]
        double[][] pw = new double[graph.length][graph.length];

        for (int v = 0; v < pw.length; v++) {
            pw[v][0] = (double) winprob[v] / (winprob[v] + looseprob[v]);
        }
        for (int l = 1; l < pw.length; l++) {
            for (int v = 0; v < pw.length; v++) {
                double best = pw[v][l-1];
                for (int a = 0; a < graph.length; a++) {
                    if (adj(graph, v, a)) {
                        double p = winprob[a] + (100. - winprob[a] - looseprob[a]) * pw[a][l - 1];
                        p /= 100.;
                        best = Math.max(best, p);
                    }
                }
                pw[v][l] = best;
            }
        }
        return pw[start][graph.length - 1];
    }
}
