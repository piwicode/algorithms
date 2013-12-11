import java.util.Arrays;

/*
 * Solution proposal to coursera Algorithms Part 2
 * Programming Assignment 1: WordNet
 */

public class Outcast {
    // constructor takes a WordNet object

    private final WordNet wn;
    private final int[] dists;

    public Outcast(WordNet wordnet) {
        wn = wordnet;
        int count = 0;
        for (String w : wordnet.nouns()) {
            count++;
        }
        dists = new int[count];
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        Arrays.fill(dists, 0);
        int worst = 0;
        String worstNoun = null;
        for (String noun : nouns) {
            int count = 0;
            for (String wnNoun : wn.nouns()) {
                dists[count] += wn.distance(noun, wnNoun);
                if (dists[count] > worst) {
                    worst = dists[count];
                    worstNoun = wnNoun;
                }
                count++;
            }
        }
        return worstNoun;
    }

    // for unit testing of this class (such as the one below)
    public static void main(String[] args) {
    }

}
