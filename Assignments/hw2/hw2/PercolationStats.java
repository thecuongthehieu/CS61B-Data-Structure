package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    double meanValue;
    double standardDeviation;
    int numSamples;
    double[] sample;
    /**
     *
     * @param N
     * @param T
     * @param pf
     * @descriptipn Constructor: Perform T independent computational experiments on an N-by-N grid
     * and caculate Mean and Standard Deviation
     * @throws
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N, T Should Be Positive Integer");
        }
        numSamples = T;
        sample = new double[numSamples];
        for (int i = 0; i < T; ++i) {
            Percolation tmpPer = pf.make(N);
            while (!tmpPer.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                tmpPer.open(row, col);
            }
            sample[i] = (double) tmpPer.numberOfOpenSites() / (N * N);
        }
        meanValue = StdStats.mean(sample);
        standardDeviation = StdStats.stddev(sample);
    }

    /**
     *
     * @return double
     * @description Return sample mean of percolation threshold
     */
    public double mean() {
        return meanValue;
    }

    /**
     *
     * @return double
     * @description Return sample standard deviation of percolation threshold
     */
    public double stddev()  {
        return standardDeviation;
    }

    /**
     *
     * @return double
     * @description Return low endpoint of 95% confidence interval
     */
    public double confidenceLow() {
        return meanValue - 1.96 * standardDeviation / Math.sqrt(numSamples);
    }

    /**
     *
     * @return double
     * @description high endpoint of 95% confidence interval
     */
    public double confidenceHigh() {
        return meanValue + 1.96 * standardDeviation / Math.sqrt(numSamples);
    }
}

