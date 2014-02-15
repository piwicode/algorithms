/*
 * Solution proposal to TopCoder Single Round Match
 */
package smr609;

/**
 *
 * @author Pierre
 */
public class PackingBallsDiv2 {

    private static int idx(int R, int G, int B) {
        return R + G * 101 + B * 101 * 101;
    }

    public static int minPacks(int R, int G, int B) {
        int[] tbl = new int[idx(100, 100, 100) + 1];
        return best(R, G, B, tbl);
    }

    private static int best(int R, int G, int B, int[] tbl) {
        if (R < 0) return Integer.MAX_VALUE;
        if (G < 0) return Integer.MAX_VALUE;
        if (B < 0) return Integer.MAX_VALUE;
        if (R == 0 && G == 0 && B == 0) return 0;
        int idx = idx(R, G, B);
        if (tbl[idx] != 0) return tbl[idx];
        int best = Integer.MAX_VALUE;
        if (R != 0) best = Math.min(best, best(Math.max(0, R - 3), G, B, tbl));
        if (G != 0) best = Math.min(best, best(R, Math.max(0, G - 3), B, tbl));
        if (B != 0) best = Math.min(best, best(R, G, Math.max(0, B - 3), tbl));
        best = Math.min(best, best(R - 1, G - 1, B - 1, tbl));
        best = Math.min(best, best(R - 1, G - 1, B, tbl));
        best = Math.min(best, best(R - 1, G, B - 1, tbl));
        best = Math.min(best, best(R, G - 1, B - 1, tbl));
        return tbl[idx] = best + 1;
    }
}
