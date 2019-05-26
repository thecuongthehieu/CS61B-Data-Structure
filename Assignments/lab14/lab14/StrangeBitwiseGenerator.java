package lab14;

import lab14lib.Generator;

/**
 * @author Nguyen Cuong
 */

public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;

    public StrangeBitwiseGenerator(int period) {
        this.period = period;
        state = 0;
    }

        @Override
    public double next() {
        ++state;
        int weirdState = state & (state >>> 3) % period;

        return (double) 2 * weirdState / period - 1;
    }
}
