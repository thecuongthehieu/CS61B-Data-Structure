package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author nguyencuong
 * @description Percolation Model:
 *  1. A percolation system is represented as an n-by-n grid of sites.
 *  2. The row and column indices are integers between 1 and n
 *  3. Each site is either open (TRUE) or blocked (FALSE).
 *  4. A full site is an open site that can be connected to an open site
 *      in the top row via a chain of neighboring (left, right, up, down) open sites.
 *  5. The system percolates if there is a full site in the bottom row.
 *
 *  In other words, a system percolates if we fill all open sites connected to
 *  the top row and that process fills some open site on the bottom row.
 *
 */

public class Percolation {
    /**
     * @Constructor Create N-by-N grid, with all sites initially blocked
     * @throws IllegalArgumentException
     */
    private int N;
    private int numOpenSites;
    private WeightedQuickUnionUF fullUF;
    private WeightedQuickUnionUF noBottonUF;
    private boolean[][] gridOfSites;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Row And Col Should Be A Positive Number");
        }
        this.N = n;

        //N * N is the top-vitual site and N * N + 1 is the bottom-virtual site
        fullUF = new WeightedQuickUnionUF(N * N + 2);
        noBottonUF = new WeightedQuickUnionUF(N * N + 1);

        gridOfSites = new boolean[N][N];
        numOpenSites = 0;
    }

    /**
     *
     * @param row
     * @param col
     * @return boolean
     * @description Helper Methods: Return true if (row, col) is valid indices
     */
    private boolean isValidIndices(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param x
     * @param y
     * @return int
     * @description Helper Methods: Convert (x,y) index to An Integer for 1D WeightedQuickUnionUF Data Structure
     */
    private int xyTo1D(int x, int y) {
        return x * N + y;
    }

    private void union(int row1, int col1, int row2, int col2) {
        fullUF.union(xyTo1D(row1, col1), xyTo1D(row2, col2));
        noBottonUF.union(xyTo1D(row1, col1), xyTo1D(row2, col2));
    }

    private void connect(int row, int col) {
        if (row > 0) {
            if (isOpen(row - 1, col)) {
                union(row, col, row - 1, col);
            }
        }
        if (row < N - 1) {
            if (isOpen(row + 1, col)) {
                union(row, col, row + 1, col);
            }
        }
        if (col > 0) {
            if (isOpen(row, col - 1)) {
                union(row, col, row, col - 1);
            }
        }
        if (col < N - 1) {
            if (isOpen(row, col + 1)) {
                union(row, col, row, col + 1);
            }
        }

        if (row == 0) {
            // Connect to Top Virtual Site
            fullUF.union(N * N, xyTo1D(row, col));
            noBottonUF.union(N * N, xyTo1D(row, col));
        }

        if (row == N - 1) {
            // Connect to Bottom Virtual Site
            fullUF.union(N * N + 1, xyTo1D(row, col));
        }
    }

    /**
     *
     * @param row
     * @param col
     * @description Open the site (row, col) if it is not open already
     * @throws IndexOutOfBoundsException
     */
    public void open(int row, int col) {
        if (!isValidIndices(row, col)) {
            throw new IndexOutOfBoundsException("(row, col) Is Not Invalid");
        }
        if (!isOpen(row, col)) {
            gridOfSites[row][col] = true;
            ++numOpenSites;
            connect(row, col);
        }
    }

    /**
     *
     * @param row
     * @param col
     * @return boolean
     * @description return true if (row,col) is open and vice versa
     * @throws IndexOutOfBoundsException
     */
    public boolean isOpen(int row, int col) {
        if (!isValidIndices(row, col)) {
            throw new IndexOutOfBoundsException("(row, col) Is Not Invalid");
        }
        return gridOfSites[row][col];
    }

    /**
     *
     * @param row
     * @param col
     * @return boolean
     * @description return true if (row, col) full and vice versa
     * @throws IndexOutOfBoundsException
     */
    public boolean isFull(int row, int col) {
        if (!isValidIndices(row, col)) {
            throw new IndexOutOfBoundsException("(row, col) Is Not Invalid");
        }
        return isOpen(row, col) && noBottonUF.connected(N * N, xyTo1D(row, col));
    }

    /**
     *
     * @return int
     * @description return the number of open sites
     */
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    /**
     * @return boolean
     * @description Does the system percolates ? Return true if yes and vice versa
     */
    public boolean percolates() {
        return fullUF.connected(N * N, N * N + 1);
    }
    /**
     * @description: For Testing
     */
    public static void main(String[] args) {
        Percolation per = new Percolation(5);

        per.open(3, 4);
        per.open(2, 4);
        per.open(2, 2);
        per.open(2, 3);
        per.open(0, 2);
        per.open(1, 2);
        per.open(4, 0);
        per.open(4, 1);
        per.open(4, 4);

        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                if (per.isFull(i, j))
                    System.out.print("(" + i + "," + j + ")" + " ");
            }
            System.out.println();
        }

        System.out.println(per.percolates());
    }
}
