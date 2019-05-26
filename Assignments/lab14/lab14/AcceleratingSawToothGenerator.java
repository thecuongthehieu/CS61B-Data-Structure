package lab14;

import lab14lib.Generator;

/**
 * @author Nguyen Cuong
 */
public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private int state;
    private double acceleration;

    public AcceleratingSawToothGenerator(int period, double a) {
        this.period = period;
        this.state = 0;
        this.acceleration = a;

    }

    @Override
    public double next() {
        ++state;
        state %= period;
        period = state == 0 ? (int) (period * acceleration) : period;

        return (double) 2 * state / period - 1;
    }
}
