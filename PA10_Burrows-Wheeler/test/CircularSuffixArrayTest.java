/*
 * Solution proposal to coursera Algorithms Part 2
 * Programming Assignment 5: Burrow-Wheeler
 */
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class CircularSuffixArrayTest {

    public CircularSuffixArrayTest() {
    }

    @Test
    public void test_empty_string() {
        final CircularSuffixArray csa = new CircularSuffixArray("");
        assertEquals(0, csa.length());
    }

    @Test
    public void test_1L_string() {
        final CircularSuffixArray csa = new CircularSuffixArray("A");
        assertEquals(1, csa.length());
        assertEquals(0, csa.index(0));
    }

    @Test
    public void test_2L_string() {
        final CircularSuffixArray csa = new CircularSuffixArray("BA");
        assertEquals(2, csa.length());
        assertEquals(1, csa.index(0));
        assertEquals(0, csa.index(1));
    }

    @Test
    public void test_3L_string() {
        final CircularSuffixArray csa = new CircularSuffixArray("BAC");
        assertEquals(3, csa.length());
        assertEquals(1, csa.index(0));
        assertEquals(0, csa.index(1));
        assertEquals(2, csa.index(2));
    }
    
    @Test
    public void test_ABRACADABRA_string() {
        final CircularSuffixArray csa = new CircularSuffixArray("ABRACADABRA!");
        assertEquals(12, csa.length());
        assertEquals(11, csa.index(0));
        assertEquals(10, csa.index(1));
        assertEquals(7, csa.index(2));
        assertEquals(0, csa.index(3));
        assertEquals(3, csa.index(4));
        assertEquals(5, csa.index(5));
        assertEquals(8, csa.index(6));
        assertEquals(1, csa.index(7));
        assertEquals(4, csa.index(8));
        assertEquals(6, csa.index(9));
        assertEquals(9, csa.index(10));
        assertEquals(2, csa.index(11));
    }
}
