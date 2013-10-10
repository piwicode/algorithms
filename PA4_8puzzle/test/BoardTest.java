/*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 4: 8 Puzzle
 */


import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class BoardTest {

    private final Board a1 = new Board(new int[][]{{0, 2, 3}, {4, 5, 6}, {7, 8, 1}});
    private final Board a2 = new Board(new int[][]{{1, 0, 3}, {4, 5, 6}, {7, 8, 2}});
    private final Board a3 = new Board(new int[][]{{1, 2, 0}, {4, 5, 6}, {7, 8, 3}});
    private final Board a4 = new Board(new int[][]{{1, 2, 3}, {0, 5, 6}, {7, 8, 4}});
    private final Board a5 = new Board(new int[][]{{1, 2, 3}, {4, 0, 6}, {7, 8, 5}});
    private final Board a6 = new Board(new int[][]{{1, 2, 3}, {4, 5, 0}, {7, 8, 6}});
    private final Board a7 = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {0, 8, 7}});
    private final Board a8 = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 8}});
    private final Board a9 = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
    private final Board[] brds = {a1, a2, a3, a4, a5, a6, a7, a8, a9};

    @Test
    public void testSolutionIsGoal() {
        assertTrue(a9.isGoal());
    }

    @Test
    public void testSolutionEquality() {
        assertTrue(a9.equals(new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}})));
        assertTrue(a9.equals(a9));
        assertFalse(a9.equals(null));
        assertFalse(a9.equals(5));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(i == j, brds[i].equals(brds[j]));
            }
        }
    }

    @Test
    public void testHamming() {
        assertEquals(1, a1.hamming());
        assertEquals(1, a2.hamming());
        assertEquals(1, a3.hamming());
        assertEquals(1, a4.hamming());
        assertEquals(1, a5.hamming());
        assertEquals(1, a6.hamming());
        assertEquals(1, a7.hamming());
        assertEquals(1, a8.hamming());
        assertEquals(0, a9.hamming());
    }

    @Test
    public void testSolutionManhattan() {
        assertEquals(4, a1.manhattan());
        assertEquals(3, a2.manhattan());
        assertEquals(2, a3.manhattan());
        assertEquals(3, a4.manhattan());
        assertEquals(2, a5.manhattan());
        assertEquals(1, a6.manhattan());
        assertEquals(2, a7.manhattan());
        assertEquals(1, a8.manhattan());
        assertEquals(0, a9.manhattan());
    }

    @Test
    public void testSolutionToString() {
        assertEquals("3\n 1 2 3\n 4 5 6\n 7 8 0\n", a9.toString());
        assertEquals("3\n 0 2 3\n 4 5 6\n 7 8 1\n", a1.toString());
    }

    @Test
    public void testSolutionDim() {
        assertEquals(3, a9.dimension());
        assertEquals(3, a1.dimension());
    }

    @Test
    public void testSolutionNeighbourA9() {
        final ArrayList<Board> en = new ArrayList<Board>();
        en.add(new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 8}}));
        en.add(new Board(new int[][]{{1, 2, 3}, {4, 5, 0}, {7, 8, 6}}));
        for (Board n : a9.neighbors()) {
            assertTrue(en.remove(n));
        }
        assertTrue(en.isEmpty());
    }

    @Test
    public void testSolutionNeighbourA1() {
        final ArrayList<Board> en = new ArrayList<Board>();
        en.add(new Board(new int[][]{{2, 0, 3}, {4, 5, 6}, {7, 8, 1}}));
        en.add(new Board(new int[][]{{4, 2, 3}, {0, 5, 6}, {7, 8, 1}}));
        assertSameNeighbours(en, a1);
    }

    @Test
    public void testSolutionNeighbourA2() {
        final ArrayList<Board> en = new ArrayList<Board>();

        en.add(new Board(new int[][]{{0, 1, 3}, {4, 5, 6}, {7, 8, 2}}));
        en.add(new Board(new int[][]{{1, 3, 0}, {4, 5, 6}, {7, 8, 2}}));
        en.add(new Board(new int[][]{{1, 5, 3}, {4, 0, 6}, {7, 8, 2}}));
        assertSameNeighbours(en, a2);
    }

    @Test
    public void testSolutionNeighbourA3() {
        final ArrayList<Board> en = new ArrayList<Board>();

        en.add(new Board(new int[][]{{1, 0, 2}, {4, 5, 6}, {7, 8, 3}}));
        en.add(new Board(new int[][]{{1, 2, 6}, {4, 5, 0}, {7, 8, 3}}));
        assertSameNeighbours(en, a3);
    }

    @Test
    public void testSolutionNeighbourA4() {
        final ArrayList<Board> en = new ArrayList<Board>();

        en.add(new Board(new int[][]{{0, 2, 3}, {1, 5, 6}, {7, 8, 4}}));
        en.add(new Board(new int[][]{{1, 2, 3}, {5, 0, 6}, {7, 8, 4}}));
        en.add(new Board(new int[][]{{1, 2, 3}, {7, 5, 6}, {0, 8, 4}}));
        assertSameNeighbours(en, a4);
    }

    @Test
    public void testSolutionNeighbourA5() {
        final ArrayList<Board> en = new ArrayList<Board>();
        en.add(new Board(new int[][]{{1, 2, 3}, {0, 4, 6}, {7, 8, 5}}));
        en.add(new Board(new int[][]{{1, 2, 3}, {4, 6, 0}, {7, 8, 5}}));
        en.add(new Board(new int[][]{{1, 0, 3}, {4, 2, 6}, {7, 8, 5}}));
        en.add(new Board(new int[][]{{1, 2, 3}, {4, 8, 6}, {7, 0, 5}}));
        assertSameNeighbours(en, a5);
    }

    @Test
    public void testSolutionNeighbourA6() {
        final ArrayList<Board> en = new ArrayList<Board>();
        en.add(new Board(new int[][]{{1, 2, 3}, {4, 0, 5}, {7, 8, 6}}));
        en.add(new Board(new int[][]{{1, 2, 0}, {4, 5, 3}, {7, 8, 6}}));
        en.add(new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}));
        assertSameNeighbours(en, a6);
    }

    @Test
    public void testSolutionNeighbourA7() {
        final ArrayList<Board> en = new ArrayList<Board>();
        en.add(new Board(new int[][]{{1, 2, 3}, {0, 5, 6}, {4, 8, 7}}));
        en.add(new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {8, 0, 7}}));
        assertSameNeighbours(en, a7);
    }

    @Test
    public void testSolutionNeighbourA8() {
        final ArrayList<Board> en = new ArrayList<Board>();
        en.add(new Board(new int[][]{{1, 2, 3}, {4, 0, 6}, {7, 5, 8}}));
        en.add(new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {0, 7, 8}}));
        en.add(new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}));
        assertSameNeighbours(en, a8);
    }

   

    private void assertSameNeighbours(final ArrayList<Board> ref, Board board) {
        for (Board n : board.neighbors()) {
            assertTrue(n.toString(), ref.remove(n));
        }
        assertTrue(ref.isEmpty());
    }
}
