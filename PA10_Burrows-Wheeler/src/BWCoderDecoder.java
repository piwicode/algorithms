
/*
 * Solution proposal to coursera Algorithms Part 2
 * Programming Assignment 5: Burrow-Wheeler
 */
public class BWCoderDecoder {

    private static int findFirst(final CircularSuffixArray csa) {
        for (int i = 0; i < csa.length(); i++) {
            if (csa.index(i) == 0) {
                return i;
            }
        }
        throw new IllegalArgumentException();
    }

    public static void code(String in, CharSink out) {
        final CircularSuffixArray csa = new CircularSuffixArray(in);
        int first = findFirst(csa);
        out.write((char) ((first >>> 24) & 0xff));
        out.write((char) ((first >>> 16) & 0xff));
        out.write((char) ((first >>> 8) & 0xff));
        out.write((char) ((first) & 0xff));
        for (int i = 0; i < csa.length(); i++) {
            int pos = csa.index(i) + csa.length() - 1;
            char c = in.charAt(pos < csa.length() ? pos : pos - csa.length());
            out.write(c);
        }
    }

    public static void decode(int first, CharSequence in, CharSink out) {
        int[] count = firstIdxInSortedArray(in);
        final int[] next = new int[in.length()];
        for (int i = 0; i < in.length(); i++) {
            int n = count[in.charAt(i)]++;
            next[n] = i;
        }

        int cur = next[first], len = in.length();
        for (int i = 0; i < len; i++) {
            out.write(in.charAt(cur));
            cur = next[cur];
        };
    }

    static int[] firstIdxInSortedArray(CharSequence in) {
        final int[] count = new int[256];
        for (int i = 0; i < in.length(); i++) {
            count[in.charAt(i)]++;
        }
        int sum = 0;
        for (int i = 0; i < count.length; i++) {
            int p = count[i];
            count[i] = sum;
            sum += p;
        }
        return count;
    }
}
