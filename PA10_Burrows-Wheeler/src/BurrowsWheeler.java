/*
 * Solution proposal to coursera Algorithms Part 2
 * Programming Assignment 5: Burrow-Wheeler
 */
public class BurrowsWheeler {
    // apply Burrows-Wheeler encoding, reading from standard input and writing to standard output

    public static void encode() {
        final StringBuilder chars = readAll();
        BWCoderDecoder.code(chars.toString(), new CharSinkImpl());
        BinaryStdOut.flush();
    }

// apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode() {
        //Parse input
        final int first = BinaryStdIn.readInt();
        final StringBuilder in = readAll();
        // Decode
        BWCoderDecoder.decode(first, in, new CharSinkImpl());
        BinaryStdOut.flush();
    }

    private static StringBuilder readAll() {
        final StringBuilder sb = new StringBuilder();
        while (!BinaryStdIn.isEmpty()) {
            sb.append(BinaryStdIn.readChar());
        }
        return sb;
    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args) {
        switch (args[0]) {
            case "-":
                encode();
                break;
            case "+":
                decode();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static class CharSinkImpl implements CharSink {

        @Override
        public void write(char c) {
            BinaryStdOut.write(c);
        }
    }

}
