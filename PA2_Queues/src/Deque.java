import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 2: Randomized Queues and Deques
 */
public class Deque<Item> implements Iterable<Item> {

    private static class Element<Item> {

        final Item item;
        Element<Item> prev, next;

        public Element(Item item, Element<Item> prev, Element<Item> next) {
            if (item == null) {
                throw new NullPointerException();
            }
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
    private int size;
    private Element<Item> first;
    private Element<Item> last;

    /**
     * construct an empty deque
     */
    public Deque() {
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

    /**
     * insert the item at the front
     */
    public void addFirst(Item item) {

        first = new Element<Item>(item, null, first);
        if (first.next != null) {
            first.next.prev = first;
        } else {
            last = first;
        }
        size++;
    }

    /**
     * insert the item at the end
     */
    public void addLast(Item item) {
        last = new Element<Item>(item, last, null);
        if (last.prev != null) {
            last.prev.next = last;
        } else {
            first = last;
        }
        size++;
    }

    /**
     * delete and return the item at the front
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        size--;
        Element<Item> e = first;
        first = e.next;
        if (first != null) {
            first.prev = null;
        } else {
            last = null;
        }
        return e.item;
    }

    /**
     * delete and return the item at the end
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        size--;
        Element<Item> e = last;
        last = e.prev;
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        return e.item;
    }

    /**
     * return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Element<Item> cur = first;

            @Override
            public boolean hasNext() {
                return cur != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Item i = cur.item;
                cur = cur.next;
                return i;
            }

            @Override
            public void remove() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                if (cur == first) {
                    removeFirst();
                } else if (cur == last) {
                    removeLast();
                } else {
                    size--;
                    cur.prev.next = cur.next;
                    cur.next.prev = cur.prev;
                }
            }
        };
    }
}
