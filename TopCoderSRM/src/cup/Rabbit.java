/*
 * Solution proposal to TopCoder Single Round Match
 */
package cup;

/**
 *
 * A(1) = ()
 * A(2) = (())
 * A(3) = (()) ()
 * A(4) = (())() (())
 * A(5) = (())()(()) (())()
 * A(N) = A(N-1) + A(N-2)
 * I/P: N, i
 * O/P: A(N).charAt(i);
 * Interviewer was not looking at iterative or recursive solution. He was looking at a closed form solution.
 */
public class Rabbit {
    static char get(int N, int i) {
        if (N == 1) return "()".charAt(i);
        else return "(())()".charAt(cut(4, 6, i));
    }

    static int cut(int a, int b, int i) {
        int n = a + b;
        if (i >= n) i = cut(b, n, i);
        return i >= b ? i - b : i;
    }

    static char bfGet(int N, int i) {
        return bfConcat(N).charAt(i);
    }

    static String bfConcat(int N) {
        if (N == 1) return "()";
        if (N == 2) return "(())";
        return bfConcat(N - 1) + bfConcat(N - 2);
    }
}
