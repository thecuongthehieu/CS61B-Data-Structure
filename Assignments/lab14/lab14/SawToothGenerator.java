package lab14;

import lab14lib.Generator;

/**
 * @author Nguyen Cuong
 */

public class SawToothGenerator implements Generator {
    private int period;
    private int state;

    public SawToothGenerator(int period) {
        this.period = period;
        state = 0;
    }

    @Override
    public double next() {
        ++state;
        state %= period;

        return (double) 2 * state / period - 1;
    }
}
