import java.util.Arrays;

/*
 * Solution proposal to coursea Algorithms Part 2
 * Programming Assignment 1: WordNet
 */
/**
 *
 * @author Pierre
 */
public class Outcast {
    // constructor takes a WordNet object

    private WordNet wn;
    private int[] dists;

    public Outcast(WordNet wordnet) {
        this.wn = wordnet;
        int count = 0;
        for (String w : wordnet.nouns()) {
            count++;
        }
        dists = new int[count];
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        Arrays.fill(nouns, 0);
        int worst = 0;
        String worstNoun = null;
        for (String noun : nouns) {
            int count = 0;
            for (String wnNoun : wn.nouns()) {
                dists[count] += wn.distance(noun, wnNoun);
                if (dists[count] > worst) {
                    worst = dists[count];
                    worstNoun = noun;
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
