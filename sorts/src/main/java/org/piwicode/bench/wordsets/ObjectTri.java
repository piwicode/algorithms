/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.wordsets;
import java.util.Arrays;

/**
 *
 * @author Pierre
 */
public class ObjectTri {

    private char[] letters = new char[0];
    private ObjectTri[] nodes = new ObjectTri[0];
    private boolean terminal = false;

    boolean contains(CharSequence sequence) {
        return contains(sequence, 0);
    }

    private boolean contains(CharSequence sequence, int indice) {
        if (indice == sequence.length()) {
            return terminal;
        }
        final char c = sequence.charAt(indice);
        final int p = Arrays.binarySearch(letters, c);
        return p < 0 ? false : nodes[p].contains(sequence, indice + 1);
    }

    boolean add(CharSequence sequence) {
        return add(sequence, 0);
    }

    private boolean add(CharSequence sequence, int indice) {
        if (indice == sequence.length()) {
            boolean didExist = terminal;
            terminal = true;
            return !didExist;
        }

        char c = sequence.charAt(indice);
        int p = Arrays.binarySearch(letters, c);
        if (p < 0) {
            p = -p - 1;
            nodes = Arrays.copyOf(nodes, nodes.length + 1);
            System.arraycopy(nodes, p, nodes, p + 1, nodes.length - p - 1);
            nodes[p] = new ObjectTri();
            letters = Arrays.copyOf(letters, letters.length + 1);
            System.arraycopy(letters, p, letters, p + 1, letters.length - p - 1);
            letters[p] = c;
            assert (isOrdered(letters));
            assert nodes.length == letters.length;
        }
        return nodes[p].add(sequence, indice + 1);
    }

    private static boolean isOrdered(char[] letters) {
        for (int i = 1; i < letters.length; i++) {
            if (letters[i - 1] >= letters[i]) {
                return false;
            }
        }
        return true;
    }

    CompactTri toCompactTri() {
        final IntBuffer intBuffer = new IntBuffer();
        buildCompactTri(intBuffer);
        return new CompactTri(intBuffer.toArray());
    }

    CompactTriHybrid toCompactTriHybrid(int X) {
        final IntBuffer intBuffer = new IntBuffer();
        buildCompactTri(intBuffer);
        return new CompactTriHybrid(intBuffer.toArray(),X);
    }

    public class IntBuffer {

        int[] buf = new int[64];
        int size = 0;

        void zero(int repeat) {
            ensureCapacity(size + repeat);
            size += repeat;
        }

        void add(int value) {
            ensureCapacity(size + 1);
            buf[size++] = value;
        }

        void put(int offset, int value) {
            buf[offset] = value;
        }

        private void ensureCapacity(int c) {
            int newSize = buf.length;
            while (newSize < c) {
                newSize *= 2;
            }
            if (newSize != buf.length) {
                buf = Arrays.copyOf(buf, newSize);
            }
        }

        int[] toArray() {
            return Arrays.copyOf(buf, size);
        }

        int size() {
            return size;
        }
    }

    private void buildCompactTri(IntBuffer buf) {
        buf.add((nodes.length << 1) + (terminal ? 1 : 0));
        for (int i = 0; i < letters.length; i++) {
            buf.add(letters[i]);
        }
        int ptrs = buf.size();
        buf.zero(nodes.length);
        for (final ObjectTri node : nodes) {
            buf.put(ptrs++, buf.size());
            node.buildCompactTri(buf);
        }
    }

    public static ObjectTri fromList(final Iterable<String> iterable) {
        final ObjectTri objectTri = new ObjectTri();
        for (CharSequence word : iterable) {
            objectTri.add(word);
        }
        return objectTri;
    }
}
