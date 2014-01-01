/*
 * Solution proposal to coursera Algorithms Part 2
 * Programming Assignment 5: Burrow-Wheeler
 */

/**
 *
 * @author Pierre
 */
class CharSinkBuf implements CharSink {

    final StringBuilder buf = new StringBuilder();

    @Override
    public void write(char c) {
        buf.append(c);
    }

}
