/*
 * Solution proposal to coursera Algorithms Part 1
 * Programming Assignment 1: Percolation
 */
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Pierre
 */
public class PercolationTest {

    public PercolationTest() {
    }

    @Test
    public void testOpen() {
        Percolation p = new Percolation(2);
        assertFalse(p.isOpen(1, 1));
        assertFalse(p.isOpen(1, 2));
        assertFalse(p.isOpen(2, 1));
        assertFalse(p.isOpen(2, 2));
        assertFalse(p.isFull(1, 1));
        assertFalse(p.isFull(1, 2));
        assertFalse(p.isFull(2, 1));
        assertFalse(p.isFull(2, 2));
        assertFalse(p.percolates());

        p.open(1, 1);
        assertTrue(p.isOpen(1, 1));
        assertFalse(p.isOpen(1, 2));
        assertFalse(p.isOpen(2, 1));
        assertFalse(p.isOpen(2, 2));
        assertTrue(p.isFull(1, 1));
        assertFalse(p.isFull(1, 2));
        assertFalse(p.isFull(2, 1));
        assertFalse(p.isFull(2, 2));
        assertFalse(p.percolates());

        p.open(2, 2);
        assertTrue(p.isOpen(1, 1));
        assertFalse(p.isOpen(1, 2));
        assertFalse(p.isOpen(2, 1));
        assertTrue(p.isOpen(2, 2));
        assertTrue(p.isFull(1, 1));
        assertFalse(p.isFull(1, 2));
        assertFalse(p.isFull(2, 1));
        assertFalse(p.isFull(2, 2));
        assertFalse(p.percolates());

        p.open(1, 2);
        assertTrue(p.isOpen(1, 1));
        assertTrue(p.isOpen(1, 2));
        assertFalse(p.isOpen(2, 1));
        assertTrue(p.isOpen(2, 2));
        assertTrue(p.isFull(1, 1));
        assertTrue(p.isFull(1, 2));
        assertFalse(p.isFull(2, 1));
        assertTrue(p.isFull(2, 2));
        assertTrue(p.percolates());
    }

    @Test
    public void testNoBackWash() {
        Percolation p = new Percolation(3);
        p.open(1, 1);
        p.open(2, 1);
        p.open(3, 1);
        p.open(3, 3);
        assertTrue(p.isOpen(1, 1));
        assertFalse(p.isOpen(1, 2));
        assertFalse(p.isOpen(1, 3));
        assertTrue(p.isOpen(2, 1));
        assertFalse(p.isOpen(2, 2));
        assertFalse(p.isOpen(2, 3));
        assertTrue(p.isOpen(3, 1));
        assertFalse(p.isOpen(3, 2));
        assertTrue(p.isOpen(3, 3));

        assertTrue(p.isFull(1, 1));
        assertFalse(p.isFull(1, 2));
        assertFalse(p.isFull(1, 3));
        assertTrue(p.isFull(2, 1));
        assertFalse(p.isFull(2, 2));
        assertFalse(p.isFull(2, 3));
        assertTrue(p.isFull(3, 1));
        assertFalse(p.isFull(3, 2));
        assertFalse(p.isFull(3, 3));

        assertTrue(p.percolates());
    }

    @Test
    public void test1x1() {
        Percolation p = new Percolation(1);
        assertFalse(p.isOpen(1, 1));
        assertFalse(p.isFull(1, 1));
        assertFalse(p.percolates());
        
        p.open(1, 1);
        assertTrue(p.isOpen(1, 1));
        assertTrue(p.isFull(1, 1));
        assertTrue(p.percolates());
    }

    @Test
    public void testPercolates() {
    }

}
