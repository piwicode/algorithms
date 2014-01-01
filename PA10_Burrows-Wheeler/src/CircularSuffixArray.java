import java.util.Arrays;

/*
 * Solution proposal to coursera Algorithms Part 2
 * Programming Assignment 5: Burrow-Wheeler
 */
public class CircularSuffixArray {

    final private Suffix[] array;

    private static class Suffix implements Comparable<Suffix> {

        private final String txt;
        private final int pos;

        private Suffix(String txt, int pos) {
            this.txt = txt;
            this.pos = pos;
        }

        private int charAt(int i) {
            i += pos;
            return txt.charAt(i > txt.length() ? i - txt.length() : i);
        }

        @Override
        public int compareTo(Suffix o) {
            for (int i = 0; i < txt.length(); i++) {
                int d = charAt(i) - o.charAt(i);
                if (d != 0) return d;
            }
            return 0;
        }

    }

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        array = new Suffix[s.length()];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Suffix(s, i);
        }
        Arrays.sort(array);
    }

    // length of s
    public int length() {
        return array.length;
    }
    // returns index of ith sorted suffix

    public int index(int i) {
        return array[i].pos;
    }
}
