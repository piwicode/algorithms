/*
 * Solution proposal to coursera Algorithms Part 2
 * Programming Assignment 5: Burrow-Wheeler
 */

public class MTFCoderDecoder {

    private final byte[] array;

    public MTFCoderDecoder() {
        array = new byte[256];
        for (int i = 0; i < array.length; i++) {
            array[i] = (byte) i;
        }
    }

    public byte encode(byte b) {
        byte p = b;
        for (int i = 0; i < array.length; i++) {
            final byte c = array[i];
            array[i] = p;
            p = c;
            if (c == b) return (byte) i;
        }
        throw new AssertionError();
    }

    public byte decode(byte b) {
        int i = b & 0xff;
        int r = array[i];
        for (; i > 0; i--) {
            array[i] = array[i - 1];
        }
        return array[0] = (byte) r;
    }
}
