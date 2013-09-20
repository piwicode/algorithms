/*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 1: Percolation
 */

public class PercolationStats {

    private final double mean, stddev;
    private final int n, t;
    private final double confidenceLo, confidenceHi;

    /**
     * perform T independent computational experiments on an N-by-N grid
     */
    public PercolationStats(int N, int T) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        if (T <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = N;
        this.t = T;
        final double results[] = new double[T];
        for (int t = 0; t < T; t++) {
            results[t] = doPercolation(N);
        }
        mean = StdStats.mean(results);
        stddev = StdStats.stddev(results);
        confidenceLo = mean - 1.96 * stddev / Math.sqrt(T);
        confidenceHi = mean + 1.96 * stddev / Math.sqrt(T);
    }

    /**
     * sample mean of percolation threshold
     */
    public double mean() {
        return mean;
    }

    /**
     * sample standard deviation of percolation threshold
     */
    public double stddev() {
        return stddev;
    }

    /**
     * returns lower bound of the 95% confidence interval
     */
    public double confidenceLo() {
        return confidenceLo;
    }

    /**
     * returns upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        return confidenceHi;
    }

    /**
     * test client, described below
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException();
        }
        final int N = Integer.parseInt(args[0]);
        final int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N, T);
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi);
    }

    private double doPercolation(int N) {
        int N2 = N * N;
        int[] shuffled = new int[N2];
        for (int r = 0; r < N2; r++) {
            shuffled[r] = r;
        }
        StdRandom.shuffle(shuffled);
        Percolation p = new Percolation(N);

        for (int r = 0; r < N2; r++) {
            int i = shuffled[r] / N + 1;
            int j = shuffled[r] % N + 1;
            p.open(i, j);
            //System.out.println("open ("+i+","+j+")");
            //p.show();
            if (p.percolates()) {
                return (double) r / N2;
            }
        }
        return 1.;

    }
}
