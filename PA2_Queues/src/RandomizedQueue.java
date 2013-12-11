import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Solution proposal to coursera Algorithms Part 1
 * Programming Assignment 2: Randomized Queues and Deques
 */
/**
 *
 * @author Pierre
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] array;

    /**
     * is the queue empty?
     */
    public RandomizedQueue() {
        size = 0;
        array = (Item[]) new Object[8];
    }

    /**
     * construct an empty randomized queue
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return the number of items on the queue
     */
    public int size() {
        return size;
    }

    /**
     * add the item
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (size == array.length) {
            resizeTo(array.length * 2);
        }
        array[size++] = item;
    }

    /**
     * delete and return a random item
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        swap(array, StdRandom.uniform(size), size - 1);
        final Item res = array[size - 1];
        array[size - 1] = null;
        size--;

        if (size < array.length / 4) {
            resizeTo(array.length / 2);
        }
        return res;
    }

    private void swap(Item[] buf, int a, int b) {
        final Item t = buf[a];
        buf[a] = buf[b];
        buf[b] = t;
    }

    /**
     * return (but do not delete) a random item
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return array[StdRandom.uniform(size)];
    }

    /**
     * return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int pos = 0;
            private final Item[] local = array.clone();

            @Override
            public boolean hasNext() {
                return pos < size;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                swap(local, pos, StdRandom.uniform(pos, size));
                return local[pos++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private void resizeTo(int newSize) {
        final Item[] buf = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            buf[i] = array[i];
        }
        array = buf;
    }
}
