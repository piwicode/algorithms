/*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 4: 8 Puzzle
 */
package pa4_8puzzle;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Pierre
 */
public class Board {

    private final int hamming, manhattan, dim;
    private final int[] b;
    private final int hole;

    /**
     * construct a board from an N-by-N array of blocks
     */
    public Board(int[][] blocks) {
        this(flatten(blocks), blocks.length);
    }

    private Board(int[] b, int dim) {
        this.dim = dim;
        this.b = b.clone();
        int tmpHole = -1, tmpHam = 0, tmpMan = 0;
        for (int i = 0; i < b.length; i++) {
            if (b[i] == 0) {
                tmpHole = i;
                continue;
            }
            if (b[i] != i + 1) {
                tmpHam++;
            }
            tmpMan += manhattanOf(i , b[i]-1, dim);
        }
        this.hamming = tmpHam;
        this.hole = tmpHole;
        this.manhattan = tmpMan;
    }

    private static int manhattanOf(int a, int b, int d) {
        return Math.abs((a % d) - (b % d)) + Math.abs((a / d) - (b / d));
    }

    /**
     * (where blocks[i][j] = block in row i, column j) board dimension N
     */
    public int dimension() {
        return dim;
    }

    /**
     * number of blocks out of place
     */
    public int hamming() {
        return hamming;
    }

    /**
     * sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        return manhattan;
    }

    /**
     * is this board the goal board?
     */
    public boolean isGoal() {
        return hamming == 0;
    }

    /**
     * a board obtained by exchanging two adjacent blocks in the same row
     */
    public Board twin() {
        return hole < 2 ? neighbor(dim, dim + 1) : neighbor(0, 1);
    }

    /**
     * does this board equal y?
     */
    public boolean equals(Object y) {
        return y != null && y.getClass() == Board.class && Arrays.equals(b, ((Board) y).b);
    }

    /**
     * all neighboring boards
     */
    public Iterable<Board> neighbors() {
        final ArrayList<Board> res = new ArrayList<>(4);
        if (hole % dim > 0) {
            res.add(neighbor(hole, hole - 1));
        }
        if (hole % dim < dim - 1) {
            res.add(neighbor(hole, hole + 1));
        }
        if (hole >= dim) {
            res.add(neighbor(hole, hole - dim));
        }
        if (hole < b.length - dim - 1) {
            res.add(neighbor(hole, hole + dim));
        }
        return res;
    }

    /**
     * string representation of the board (in the output format specified below)
     */
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(dim).append('\n');
        for (int i = 0, k = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                sb.append(' ').append(b[k++]);;
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private Board neighbor(final int from, final int to) {
        final int toVal = b[to];
        b[to] = b[from];
        b[from] = toVal;
        final Board result = new Board(b, dim);
        b[from] = b[to];
        b[to] = toVal;
        return result;
    }

    private static int[] flatten(int[][] blocks) {
        final int dim = blocks.length;
        final int[] res = new int[dim * dim];
        for (int i = 0, k = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                res[k++] = blocks[i][j];
            }
        }
        return res;
    }
}
