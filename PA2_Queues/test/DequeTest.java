/*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 2: Randomized Queues and Deques
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class DequeTest {

    Deque<Integer> deq = new Deque<>();

    public DequeTest() {
    }

    @Test
    public void testIsEmpty() {
        assertTrue(deq.isEmpty());
        assertEquals(0, deq.size());

        deq.addFirst(111);
        assertFalse(deq.isEmpty());
        assertEquals(1, deq.size());

        assertEquals(111, deq.removeFirst().intValue());
        assertTrue(deq.isEmpty());
        assertEquals(0, deq.size());

        deq.addFirst(222);
        assertFalse(deq.isEmpty());
        assertEquals(1, deq.size());

        assertEquals(222, deq.removeLast().intValue());
        assertTrue(deq.isEmpty());
        assertEquals(0, deq.size());

        deq.addLast(333);
        assertFalse(deq.isEmpty());
        assertEquals(1, deq.size());

        assertEquals(333, deq.removeFirst().intValue());
        assertTrue(deq.isEmpty());
        assertEquals(0, deq.size());

        deq.addLast(444);
        assertFalse(deq.isEmpty());
        assertEquals(1, deq.size());

        assertEquals(444, deq.removeLast().intValue());
        assertTrue(deq.isEmpty());
        assertEquals(0, deq.size());

        deq.addFirst(2);
        deq.addLast(3);
        deq.addFirst(1);
        deq.addLast(4);
        deq.addLast(5);
        deq.addFirst(0);
        
        assertEquals(6, deq.size());
        Iterator<Integer> iterator = deq.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(0, iterator.next().intValue());
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next().intValue());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next().intValue());
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next().intValue());
        assertTrue(iterator.hasNext());
        assertEquals(4, iterator.next().intValue());
        assertTrue(iterator.hasNext());
        assertEquals(5, iterator.next().intValue());
        assertFalse(iterator.hasNext());
        
        assertEquals(0, deq.removeFirst().intValue());
        assertEquals(1, deq.removeFirst().intValue());
        assertEquals(2,deq.removeFirst().intValue());
        assertEquals(5, deq.removeLast().intValue());
        assertEquals(4,deq.removeLast().intValue());
        assertEquals(3,deq.removeLast().intValue());
        
        
    }

    @Test(expected = NullPointerException.class)
    public void testSize() {
        deq.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddFirst() {
        deq.addLast(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testAddLast() {
        deq.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFirst() {
        deq.removeLast();
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveLast() {
        deq.iterator().remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveLast2() {
        deq.iterator().next();
    }

}
