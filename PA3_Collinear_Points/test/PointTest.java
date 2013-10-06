/*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 3: Collinear Points
 */
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class PointTest {

    public PointTest() {
    }

    @Test
    public void compareTo() {
        // same x & y
        assertEquals(0, new Point(123, 456).compareTo(new Point(123, 456)));
        // same x
        assertEquals(-1, new Point(123, 455).compareTo(new Point(123, 456)));
        assertEquals(1, new Point(123, 457).compareTo(new Point(123, 456)));
        // same y
        assertEquals(-1, new Point(122, 456).compareTo(new Point(123, 456)));
        assertEquals(1, new Point(124, 456).compareTo(new Point(123, 456)));
    }

    @Test
    public void testSlopeTo() {
        assertEquals(Double.doubleToLongBits(Double.NEGATIVE_INFINITY), Double.doubleToLongBits(new Point(1, 2).slopeTo(new Point(1, 2))));
        assertEquals(Double.doubleToLongBits(+0.), Double.doubleToLongBits(new Point(0, 2).slopeTo(new Point(1, 2))));
        assertEquals(Double.doubleToLongBits(+0.), Double.doubleToLongBits(new Point(1, 2).slopeTo(new Point(0, 2))));
        assertEquals(Double.doubleToLongBits(Double.POSITIVE_INFINITY), Double.doubleToLongBits(new Point(1, 2).slopeTo(new Point(1, 0))));
        assertEquals(Double.doubleToLongBits(Double.POSITIVE_INFINITY), Double.doubleToLongBits(new Point(1, 0).slopeTo(new Point(1, 2))));
        assertEquals(Double.doubleToLongBits(2.), Double.doubleToLongBits(new Point(1, 2).slopeTo(new Point(3, 6))));
        assertEquals(Double.doubleToLongBits(-2.), Double.doubleToLongBits(new Point(1, 2).slopeTo(new Point(3, -2))));

    }

    @Test
    public void testDouble() {
        assertTrue(+0. == -0.);
    }

}
