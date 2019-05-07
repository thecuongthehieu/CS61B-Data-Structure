package synthesizer;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);

        assertTrue(arb.capacity() == 10);

        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());

        arb.enqueue(100);
        arb.enqueue(200);

        assertFalse(arb.isEmpty());
        assertFalse(arb.isFull());
        assertTrue(arb.fillCount() == 2);

        arb.enqueue(200);
        arb.dequeue();

        assertTrue(arb.peek() == 200);
        assertTrue(arb.capacity() == 10);
        assertTrue(arb.fillCount == 2);
        assertFalse(arb.isFull());
        assertFalse(arb.isEmpty());

        arb.dequeue();
        arb.dequeue();

        assertFalse(arb.isFull());
        assertTrue(arb.isEmpty());
        assertTrue(arb.capacity() == 10);
        assertTrue(arb.fillCount() == 0);


        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);

        assertTrue(arb.fillCount() == 5);
        int cnt = 1;

        for (int n : arb) {
            //System.out.println(n);
            assertTrue(n == cnt);
            ++cnt;
        }
        cnt = 2;
        arb.dequeue();
        for (int n : arb) {
            //System.out.println(n);
            assertTrue(n == cnt);
            ++cnt;
        }

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
