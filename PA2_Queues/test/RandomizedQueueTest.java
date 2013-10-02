/*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 2: Randomized Queues and Deques
 */
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class RandomizedQueueTest {

    public RandomizedQueueTest() {
    }

    @Test
    public void testIsEmpty() {
        final RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        assertTrue(q.isEmpty());
        q.enqueue(111);
        assertFalse(q.isEmpty());
        assertEquals(1, q.size());
        assertEquals(111, (int) q.sample());
        assertEquals(111, (int) q.dequeue());
        assertTrue(q.isEmpty());
        assertEquals(0, q.size());
    }

    @Test
    public void testEnqueue() {
        final RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        Set<Integer> s = new HashSet<Integer>();
        Set<Integer> r = new HashSet<Integer>();
        for (int i = 0; i < 32; i++) {
            s.add(i);
            r.add(i);
            q.enqueue(i);
        }
        for (Integer i : q) {
            assertTrue(r.remove(i));
        }
        assertTrue(r.isEmpty());

        while (!q.isEmpty()) {
            assertEquals(s.size(), q.size());
            assertTrue(s.remove(q.dequeue()));
            assertEquals(s.isEmpty(), q.isEmpty());
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void noRemove() {
        new RandomizedQueue<Integer>().iterator().remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeue() {
        new RandomizedQueue<Integer>().dequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void testItr() {
        new RandomizedQueue<Integer>().iterator().next();
    }

}
