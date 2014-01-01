/*
 * Solution proposal to coursera Algorithms Part 2
 * Programming Assignment 5: Burrow-Wheeler
 */

public class MoveToFront {
    // apply move-to-front encoding, reading from standard input and writing to standard output

    public static void encode() {
        final MTFCoderDecoder mtf = new MTFCoderDecoder();
        
        while (!BinaryStdIn.isEmpty()) {
            BinaryStdOut.write(mtf.encode(BinaryStdIn.readByte()));
        }
        BinaryStdOut.flush();
    }
    // apply move-to-front decoding, reading from standard input and writing to standard output

    public static void decode() {
        final MTFCoderDecoder mtf = new MTFCoderDecoder();        
        while (!BinaryStdIn.isEmpty()) {
            BinaryStdOut.write(mtf.decode(BinaryStdIn.readByte()));
        }
        BinaryStdOut.flush();
    }
    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding

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
}
