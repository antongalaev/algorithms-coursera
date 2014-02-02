/**
 * Created with IntelliJ IDEA.
 * User: anton
 * Date: 02/02/2014
 * Time: 14:52
 */
public class PercolationStats {

    private double[] experiments;

    public PercolationStats(int N, int T) {  // perform T independent computational experiments on an N-by-N grid
        if (N < 1 ||T < 1) {
            throw new IllegalArgumentException();
        }
        experiments = new double[T];
        for (int i = 0; i < T; ++i) {
            Percolation p = new Percolation(N);
            int opened = 0;
            while (!p.percolates()) {
                int row = StdRandom.uniform(N) + 1;
                int column = StdRandom.uniform(N) + 1;
                if (!p.isOpen(row, column)) {
                    p.open(row, column);
                    ++opened;
                }
            }
            experiments[i] = opened / (double) (N * N);
        }
    }

    public double mean() {  // sample mean of percolation threshold
        return StdStats.mean(experiments);
    }
    public double stddev() {          // sample standard deviation of percolation threshold
        return StdStats.stddev(experiments);
    }
    public double confidenceLo() {             // returns lower bound of the 95% confidence interval
        return mean() - (1.96 * stddev()) / Math.sqrt(experiments.length);
    }
    public double confidenceHi() { // returns upper bound of the 95% confidence interval
        return mean() + (1.96 * stddev()) / Math.sqrt(experiments.length);
    }
    public static void main(String[] args) {  // test client, described below
        if (args.length == 0) {
            return;
        }
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        StdOut.printf("mean = " + stats.mean());
        StdOut.printf("stddev = " + stats.stddev());
        StdOut.printf("95 confidence interval = " + stats.confidenceLo() + ", " +stats.confidenceHi());
    }
}

