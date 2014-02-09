/*
 * Solution proposal to TopCoder Single Round Match
 */
package cup;

/**
 * Find the smallest number m(>0) whose product of digits equal to a given number n.
 * ex:- n = 24 and m = 38
 * n = 12 and m = 26
 *
 */
public class SmallestPrd {
    static int smallestPrd(int n) {
        int r = 0, f = 1;
        for (int d = 9; d > 1; d--) {
            while (n % d == 0) {
                n /= d;
                r += f * d;
                f *= 10;
            }
        }
        return r;
    }
}
