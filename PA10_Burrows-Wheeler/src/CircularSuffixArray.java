import java.util.Arrays;

/*
 * Solution proposal to coursera Algorithms Part 2
 * Programming Assignment 5: Burrow-Wheeler
 */
public class CircularSuffixArray {

    final private int[] array;

    // circular suffix array of s
    public CircularSuffixArray(String str) {
        array = new int[str.length()];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        MSBRadixSort(str, array, 0, array.length - 1, 0);      
    }

    // length of s
    public int length() {
        return array.length;
    }
    // returns index of ith sorted suffix

    public int index(int i) {
        return array[i];
    }

    private void MSBRadixSort(String str, int[] array, int from, int to, int off) {
        if (to - from <= 0 || off >= str.length()) return;
        char p = charAt(str, array[from], off);
        int fp = from, lp = to;
        for (int i = from; i <= lp;) {
            int idx = array[i];
            char c = charAt(str, idx, off);
            if (c < p) {
                array[i] = array[fp];
                array[fp++] = idx;
                i++;
            } else if (c > p) {
                array[i] = array[lp];
                array[lp--] = idx;
            } else{
                i++;
            }
        }
        MSBRadixSort(str, array, from, fp - 1, off);
        MSBRadixSort(str, array, fp, lp, off + 1);
        MSBRadixSort(str, array, lp + 1, to, off);
    }

    private char charAt(String str, int suffixVal, int off) {
        int idx = suffixVal + off;
        char c = str.charAt(idx < str.length() ? idx : idx - str.length());
        return c;
    }
}
