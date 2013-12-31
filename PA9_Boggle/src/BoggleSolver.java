import java.util.HashSet;
import java.util.Set;

/*
 * Solution proposal to coursera Algorithms Part 2
 * Programming Assignment 3: Boggle
 */
public class BoggleSolver {
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)

    public BoggleSolver(String[] dictionary) {
        for (final String word : dictionary) {
            if (word.length() >= 3)
                insert(word);
        }
    }

    private final HashSet<String> solutions = new HashSet<>(1024);
    // Returns the set of all valid words in the given Boggle board, as an Iterable.

    public Iterable<String> getAllValidWords(BoggleBoard board) {
        solutions.clear();
        final boolean[][] used = new boolean[board.rows()][board.cols()];
        for (int c = 0; c < board.cols(); c++) {
            for (int r = 0; r < board.rows(); r++) {
                search(r, c, board, used, root, solutions);
            }
        }
        return solutions;
    }
    private static final int SCORE[] = {0, 0, 0, 1, 1, 2, 3, 5, 11};
    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)

    public int scoreOf(String word) {
        Node n = root;
        final int len = word.length();
        for (int i = 0; i < len; i++) {
            final int idx = indexFor(word.charAt(i));
            n = n.children[idx];
            if (n == null) return 0;
            if (idx == indexFor('Q')) {
                i++;
            }
        }
        return n.value != null ? SCORE[Math.min(len, 8)] : 0;
    }

    private final Node root = new Node();

    private static void search(final int r, final int c, final BoggleBoard board,
            final boolean[][] used, final Node node,
            final Set<String> solutions) {

        final char letter = board.getLetter(r, c);
        final Node n = node.children[indexFor(letter)];
        if (n != null) {
            used[r][c] = true;
            if (n.value != null)
                solutions.add(n.value);
            final int uh = Math.min(r + 2, board.rows()), vh = Math.min(c + 2, board.cols());
            for (int u = Math.max(r - 1, 0); u < uh; u++) {
                for (int v = Math.max(c - 1, 0); v < vh; v++) {
                    if (used[u][v]) continue;
                    search(u, v, board, used, n, solutions);
                }
            }
            used[r][c] = false;
        }
    }

    private final static int indexFor(final char letter) {
        return (int) letter - 'A';
    }

    static private class Node {

        private final Node[] children = new Node[26];
        private String value;
    }

    private void insert(String cs) {
        Node n = root;
        final int len = cs.length();
        for (int i = 0; i < len; i++) {
            final int idx = indexFor(cs.charAt(i));
            if (n.children[idx] == null) {
                n.children[idx] = new Node();
            }
            n = n.children[idx];
            if (idx == indexFor('Q')) {
                i++;
                if (i >= len || cs.charAt(i) != 'U')
                    return;
            }
        }
        n.value = cs;
        cs.hashCode();
    }

}
