/*
 * Solution proposal to coursera Algorithms Part 2
 * Programming Assignment 3: Boggle
 */
import com.google.common.base.Charsets;
import com.google.common.collect.Iterables;
import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class BoggleSolverTest {

    public BoggleSolverTest() {
    }
    final BoggleSolver bs = new BoggleSolver(new String[]{"A", "AB", "ABC", "ABCD", "ABCDE", "ABCDEF", "ABCDEFG", "ABCDEFGH", "ABCDEFGHI"});

    @Test
    public void test1() {
        char[][] chars = new char[][]{{'A'}};
        final Iterable<String> allValidWords = bs.getAllValidWords(new BoggleBoard(chars));
        assertArrayEquals(new String[]{}, Iterables.toArray(allValidWords, String.class));

    }

    @Test
    public void test2() {
        char[][] chars = new char[][]{{'B', 'A'}};
        final Iterable<String> allValidWords = bs.getAllValidWords(new BoggleBoard(chars));
        assertArrayEquals(new String[]{}, Iterables.toArray(allValidWords, String.class));
    }

    @Test
    public void test3() throws IOException {
        int score = scoreFor("dictionary-algs4.txt", "board-q.txt");
        assertEquals(84, score);
    }

    @Test
    public void test4() throws IOException {
        int score = scoreFor("dictionary-algs4.txt", "board4x4.txt");
        assertEquals(33, score);
    }

    @Test
    public void test5() throws IOException {
        int score = scoreFor("dictionary-yawl.txt", "board-antidisestablishmentarianisms.txt");
        assertEquals(172, score);
    }

    @Test
    public void test6() throws IOException {
        int score = scoreFor("dictionary-yawl.txt", "board-dichlorodiphenyltrichloroethanes.txt");
        assertEquals(68, score);
    }

    @Test
    public void testScore() {
        assertEquals(0, bs.scoreOf("A"));
        assertEquals(0, bs.scoreOf("AB"));
        assertEquals(1, bs.scoreOf("ABC"));
        assertEquals(1, bs.scoreOf("ABCD"));
        assertEquals(2, bs.scoreOf("ABCDE"));
        assertEquals(3, bs.scoreOf("ABCDEF"));
        assertEquals(5, bs.scoreOf("ABCDEFG"));
        assertEquals(11, bs.scoreOf("ABCDEFGH"));
        assertEquals(11, bs.scoreOf("ABCDEFGHI"));
        assertEquals(0, bs.scoreOf("BBB"));
        assertEquals(0, bs.scoreOf("B"));
        assertEquals(0, bs.scoreOf("ABCDEFGHIJ"));
    }

    private int scoreFor(final String dico, final String brd) throws IOException {
        final URL url = getClass().getResource(dico);
        final String[] dictionary = Resources.readLines(url, Charsets.UTF_8).toArray(new String[0]);
        final BoggleSolver solver = new BoggleSolver(dictionary);
        final BoggleBoard board = new BoggleBoard(getClass().getResource(brd).getFile());
        final Iterable<String> words = solver.getAllValidWords(board);
        int score = 0;
        for (String word : words) {
            score += solver.scoreOf(word);
        }
        return score;
    }

}
