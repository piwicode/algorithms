/*
 * Solution proposal to TopCoder Single Round Match
 */
package smr609;

/**
 *
 * @author Pierre
 */
public class VocaloidsAndSongs {
    public static final int X = 1000000007;

    public static int count(int s, int a, int b, int c) {
        int[][][][] tbl = new int[51][51][51][51];
        for (int i = 0; i < 51; i++) {
            for (int j = 0; j < 51; j++) {
                for (int k = 0; k < 51; k++) {
                    for (int l = 0; l < 51; l++) {
                        tbl[i][j][k][l] = -1;
                    }
                }
            }
        }
        return recurse(s, a, b, c, tbl);
    }

    private static int recurse(int s, int a, int b, int c, int[][][][] tbl) {
        if (a + b + c < s) return 0;
        if (a > s) return 0;
        if (b > s) return 0;
        if (c > s) return 0;
        if (s == 0) return 1;
        //Symetric cases does not need re-calculation: insertion sort
        int t;
        if (a < b) {
            t = a;
            a = b;
            b = t;
        }
        if (b < c) {
            t = b;
            b = c;
            c = t;
        }
        if (a < b) {
            t = a;
            a = b;
            b = t;
        }
        //Top down dynamic programming
        if (tbl[s][a][b][c] != -1) return tbl[s][a][b][c];
        int total = 0;
        if (a != 0) total = (total + recurse(s - 1, a - 1, b, c, tbl)) % X;
        if (b != 0) total = (total + recurse(s - 1, a, b - 1, c, tbl)) % X;
        if (c != 0) total = (total + recurse(s - 1, a, b, c - 1, tbl)) % X;
        if (a != 0 && b != 0) total = (total + recurse(s - 1, a - 1, b - 1, c, tbl)) % X;
        if (b != 0 && c != 0) total = (total + recurse(s - 1, a, b - 1, c - 1, tbl)) % X;
        if (c != 0 && a != 0) total = (total + recurse(s - 1, a - 1, b, c - 1, tbl)) % X;
        if (b != 0 && c != 0 && a != 0) total = (total + recurse(s - 1, a - 1, b - 1, c - 1, tbl)) % X;
        return tbl[s][a][b][c] = total;
    }
}
