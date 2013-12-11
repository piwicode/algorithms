/*
 * Solution proposal to coursera Algorithms Part 1
 * Programming Assignment 1: Percolation
 */

/**
 * model a percolation system. By convention, the indices i and j are integers
 * between 1 and N, where (1, 1) is the upper-left site: Throw a
 * java.lang.IndexOutOfBoundsException if either i or j is outside this range.
 * The constructor should take time proportional to N^2; all methods should take
 * constant time plus a constant number of calls to the union-find methods
 * union(), find(), connected(), and count().
 *
 * @author plabatut
 */
public class Percolation {

    private final int n;
    private final WeightedQuickUnionUF uf;
    private final boolean[][] cells;
    private final byte[] ud;
    private boolean percolates;

    /**
     * create N-by-N grid, with all sites blocked
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        n = N;
        cells = new boolean[n + 2][n + 2];
        ud = new byte[n * n];
        for (int i = 1; i <= n; i++) {
            ud[cid(1, i)] = 1;
            ud[cid(n, i)] |= 2; //Take care of 1x1 matix where the first and last rows are the same
        }
        uf = new WeightedQuickUnionUF(n * n);
    }

    private void checkBounds(int i, int j) {
        if (i <= 0 || i > n || j <= 0 || j > n) {
            throw new IndexOutOfBoundsException("(" + i + "," + j + ") not within " + n + " square");
        }
    }

    private int cid(int i, int j) {
        return (i - 1) * n + (j - 1);
    }

    private int union(int p, int q) {
        int proot = uf.find(p), qroot = uf.find(q);
        uf.union(proot, qroot);
        return ud[proot] = ud[qroot] = (byte) (ud[proot] | ud[qroot]);
    }

    /**
     * open site (row i, column j) if it is not already
     */
    public void open(int i, int j) {
        checkBounds(i, j);
        cells[i][j] = true;
        int r = ud[cid(i, j)];//Take care of 1x1 matrix
        if (cells[i - 1][j]) {
            r |= union(cid(i, j), cid(i - 1, j));
        }
        if (cells[i + 1][j]) {
            r |= union(cid(i, j), cid(i + 1, j));
        }
        if (cells[i][j - 1]) {
            r |= union(cid(i, j), cid(i, j - 1));
        }
        if (cells[i][j + 1]) {
            r |= union(cid(i, j), cid(i, j + 1));
        }
        percolates |= r == 3;
    }

    /**
     * is site (row i, column j) open?
     */
    public boolean isOpen(int i, int j) {
        checkBounds(i, j);
        return cells[i][j];
    }

    /**
     * is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {
        checkBounds(i, j);
        return cells[i][j] && (ud[uf.find(cid(i, j))] & 1) != 0;
    }

    /**
     * does the system percolate?
     */
    public boolean percolates() {
        return percolates;
    }

    private void show() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (cells[i][j]) {
                    System.out.print(isFull(i, j) ? '0' : '-');
                } else {
                    System.out.print('X');
                }
            }
            System.out.println();
        }
    }

}
