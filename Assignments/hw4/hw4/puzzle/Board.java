package hw4.puzzle;


import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.Queue;

/**
 * @author Nguyen Cuong
 */

public class Board implements WorldState {
    private int N;
    int[][] grid;
    Position blankPos;
    private static final int BLANK = 0;


    private class Position {
        int row;
        int col;

        public Position(int r, int c) {
            this.row = r;
            this.col = c;
        }
    }
    /**
     * @Description Constructs a board from an N-by-N array of tiles where tiles[i][j] = tile at row i, column j
     * @param tiles
     */
    public Board(int[][] tiles) {
        this.N = tiles.length;
        grid = new int[N][N];

        for (int r = 0; r < N; ++r) {
            for (int c = 0; c < N; ++c) {
                grid[r][c] = tiles[r][c];

                // Take position of 0 to caculate all neighbors;
                if (grid[r][c] == BLANK) {
                    blankPos = new Position(r, c);
                }
            }
        }
    }
    private boolean validParams(int row, int col) {
        if (row >= N || row < 0 || col >= N || col < 0) return false;
        else return true;
    }

    /**
     * @Description Returns value of tile at row i, column j (or 0 if blank)
     * @param i
     * @param j
     * @return int
     */
    public int tileAt(int i, int j) {
        if (!validParams(i, j)) {
            throw new IndexOutOfBoundsException();
        }
        return grid[i][j];
    }

    /**
     * @Description Returns the board size N
     * @return int
     */
    public int size() {
        return this.N;
    }

    /**
     * @Description Returns the neighbors of the current board. I use the recommend answer here http://joshh.ug/neighbors.html
     * @return Iterable<WroldState>
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    private Position getRightPosision(int value) {
        int rightRow = (value - 1) / N;
        int rightCol = value - N * rightRow - 1;

        return new Position(rightRow, rightCol);
    }

    /**
     * @Description Hamming estimate described below
     * @return int
     */
    public int hamming() {
        int estimatedVal = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] != BLANK) {
                    Position rightPos = getRightPosision(grid[i][j]);
                    if (i != rightPos.row || j != rightPos.col) {
                        estimatedVal += 1;
                    }
                }
            }
        }
        return estimatedVal;
    }

    /**
     * @Description Manhattan estimate described below
     * @return int
     */
    public int manhattan() {
        int estimatedVal = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] != BLANK) {
                    Position rightPos = getRightPosision(grid[i][j]);
                    estimatedVal += Math.abs(i - rightPos.row);
                    estimatedVal += Math.abs(j - rightPos.col);
                }
            }
        }

        return estimatedVal;
    }

    /**
     * @Description Estimated distance to goal. This method should
     *              simply return the results of manhattan() when submitted to
     *              Gradescope.
     * @return int
     */
    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /**
     * @Description Returns true if this board's tile values are the same
     *              position as y's
     * @param y another Object
     * @return boolean
     */
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        boolean sameSize = (this.N == that.N);
        boolean sameElems = true;
        for (int r = 0; r < N; ++r) {
            for (int c = 0; c < N; ++c) {
                if (this.grid[r][c] != that.grid[r][c]) {
                    sameElems = false;
                }
            }
        }

        return sameSize && sameElems;
    }

    /** Returns the string representation of the board.
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
