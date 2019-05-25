import java.awt.*;
import java.util.ArrayList;

import edu.princeton.cs.algs4.Picture;

/**
 * @author Nguyen Cuong
 */

public class SeamCarver {

    Picture picture;
    private SeamRemover seamRemover;

    /**
     * @Description Construct from the picture
     * @param picture
     */
    public SeamCarver(Picture picture) {
        this.picture = picture;
        this.seamRemover = new SeamRemover();
    }

    /**
     * @description return the current picture
     * @return Picture
     */
    public Picture picture() {
        return this.picture;
    }

    /**
     * @description return the width of current picture
     * @return int
     */
    public int width() {
        return this.picture.width();
    }

    /**
     * @description return the height of current picture
     * @return int
     */
    public int height() {
        return this.picture.height();
    }

    private double square(double x) {
        return x * x;
    }

    private double getSquaredDelta(int x1, int y1, int x2, int y2) {
        Color color1 = picture.get(x1, y1);
        Color color2 = picture.get(x2, y2);

        return square(color2.getRed() - color1.getRed()) + square(color2.getGreen() - color1.getGreen()) + square(color2.getBlue() - color1.getBlue());

    }

    /**
     * @description return the energy of pixel at column x and row y
     * @param x
     * @param y
     * @return double
     */
    public double energy(int x, int y) {
        int r, l;
        r = x < width() - 1 ? x + 1 : 0;
        l = x > 0 ? x - 1 : width() - 1;

        double squaredDeltaX = getSquaredDelta(r, y, l, y);

        int t, d;
        t = y > 0 ? y - 1 : height() - 1;
        d = y < height() - 1 ? y + 1 : 0;

        double squaredDeltaY = getSquaredDelta(x, t, x, d);

        return squaredDeltaX + squaredDeltaY;
    }

    /**
     * @description return the sequence of indices for horizontal seam
     * @return int[]
     */
    public int[] findHorizontalSeam() {
        int W = width();
        int H = height();

        /* Transpose Energy Map */
        double[][] energyMap = new double[H][W];
        for (int row = 0; row < picture.height(); ++row) {
            for (int col = 0; col < picture.width(); ++col) {
                energyMap[row][col] = energy(col, row);
            }
        }

        return findVerticalSeam(energyMap, H, W);
    }

    public int[] findVerticalSeam(double[][] energyMap, int W, int H) {
        double[][] Min = new double[W][H];
        int[][] trace = new int[W][H];

        for (int y = 0; y < H; ++y) {
            for (int x = 0; x < W; ++x) {
                if (y == 0) {
                    Min[x][y] = energyMap[x][y];
                    trace[x][y] = -1;
                } else {
                    Min[x][y] = Min[x][y - 1] + energyMap[x][y];
                    trace[x][y] = x;

                    if (x > 0) {
                        double minVal = Math.min(Min[x][y], Min[x - 1][y - 1] + energyMap[x][y]);
                        if (Min[x][y] > minVal) {
                            Min[x][y] = minVal;
                            trace[x][y] = x - 1;
                        }
                    }

                    if (x < W - 1) {
                        double minVal = Math.min(Min[x][y], Min[x + 1][y - 1] + energyMap[x][y]);
                        if (Min[x][y] > minVal) {
                            Min[x][y] = minVal;
                            trace[x][y] = x + 1;
                        }
                    }
                }
            }
        }

        int index = 0;
        for (int col = 0; col < W; ++col) {
            index = Min[index][H - 1] < Min[col][H- 1] ? index : col;
        }

        /* Tracing */
        int[] verticalSeam = new int[H];
        for (int row = H - 1; row >= 0; --row) {
            verticalSeam[row] = index;
            index = trace[index][row];
        }

        return verticalSeam;
    }

    /**
     * @description return the sequence of indices for vertical seam
     * @return int[]
     */
    public int[] findVerticalSeam() {
        int W = width();
        int H = height();

        double[][] energyMap = new double[W][H];
        for (int row = 0; row < H; ++row) {
            for (int col = 0; col < W; ++col) {
                energyMap[col][row] = energy(col, row);
            }
        }

        return findVerticalSeam(energyMap, W, H);
    }

    /**
     * @description Remove horizontal seam from picture
     * @param seam
     */
    public void removeHorizontalSeam(int[] seam) {
        picture = seamRemover.removeHorizontalSeam(picture, seam);
    }

    /**
     * @description remove vertical seam from picture
     * @param seam
     */
    public void removeVerticalSeam(int[] seam) {
        picture = seamRemover.removeVerticalSeam(picture, seam);

        /*
        int W = width();
        int H = height();

        Picture newPic = new Picture(W - 1, H);

        for (int row = 0; row < H; ++row) {
            int count = 0;
            for (int col = 0; col < W; ++col) {
                if (col != seam[row]) {
                    newPic.set(count, row, picture.get(col, row));
                    ++count;
                }
            }
        }

        picture = newPic;
        */
    }
}
