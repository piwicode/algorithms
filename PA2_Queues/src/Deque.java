import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Solution proposal to coursera Algorithms Part 1
 * Programming Assignment 2: Randomized Queues and Deques
 */
public class Deque<Item> implements Iterable<Item> {

    private static class Element<Item> {

        final private Item item;
        private Element<Item> prev, next;

        public Element(Item item, Element<Item> prev, Element<Item> next) {

            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
    private int size;
    private Element<Item> e = new Element(null, null, null);

    /**
     * construct an empty deque
     */
    public Deque() {
        e.next = e.prev = e;
    }

    /**
     * is the deque empty?
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return the number of items on the deque
     */
    public int size() {
        return size;
    }

    private void insert(Item i, Element<Item> b, Element<Item> a) {
        if (i == null) {
            throw new NullPointerException();
        }
        b.next = a.prev = new Element<Item>(i, b, a);
        size++;
    }

    private Item remove(Element<Item> a) {
        if (a == e) {
            throw new NoSuchElementException();
        }
        a.prev.next = a.next;
        a.next.prev = a.prev;
        size--;
        return a.item;
    }

    /**
     * insert the item at the front
     */
    public void addFirst(Item item) {
        insert(item, e, e.next);
    }

    /**
     * insert the item at the end
     */
    public void addLast(Item item) {
        insert(item, e.prev, e);
    }

    /**
     * delete and return the item at the front
     */
    public Item removeFirst() {
        return remove(e.next);
    }

    /**
     * delete and return the item at the end
     */
    public Item removeLast() {
        return remove(e.prev);
    }

    /**
     * return an iterator over items in order from front to end
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Element<Item> cur = e.next;

            @Override
            public boolean hasNext() {
                return cur != e;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                final Item item=cur.item;
                cur = cur.next;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
