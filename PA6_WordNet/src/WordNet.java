import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
 * Solution proposal to coursera Algorithms Part 2
 * Programming Assignment 1: WordNet
 */
public class WordNet {

    private final HashMap<String, Integer> words;
    private final List<Synset> synsets;
    private final SAP sap;
    private final Digraph dg;
    /**
     * constructor takes the name of the two input files
     *
     * @param synsetsFile path to the synsets file
     * @param hypernymsFile path to the hymernyms file
     * @throws NullPointerException if an input is null
     */
    public WordNet(String synsetsFile, String hypernymsFile) {
        //Read synsets from the file to a memory structure. 1 synset = 1 node
        synsets = readSynset(synsetsFile);
        //Assign an indice to each distinct word. 1 vertex=1 node
        words = indexWords(synsets);
        //Create a graph with synset & word nodes, add edges from the file
        dg = readDg(hypernymsFile, synsets.size() + words.size());//~V+E
        //Get the only root ignoring word vertices, or throw IAE
        final int root = getOnlyRoot(dg, synsets.size());//~V
        //Detect cycles
        final Digraph dgr = dg.reverse();//~E
        detectCycles(dgr, root);//~V+E
        //Add the words to the graph ~W
        for (int ssIdx = 0; ssIdx < synsets.size(); ssIdx++) {
            WordNet.Synset ss = synsets.get(ssIdx);
            for (String word : ss.words) {
                dg.addEdge(words.get(word), ssIdx);
            }
        }
        sap = new SAP(dg);
    }

    // ~V
    private static List<Synset> readSynset(final String synsetsFile) throws IllegalArgumentException {
        final List<Synset> synsets = new ArrayList();
        final In in = new In(synsetsFile);
        while (in.hasNextLine()) {
            final String[] elts = in.readLine().split(",", 3);
            if (elts.length != 3)
                throw new IllegalArgumentException("synset line " + Arrays.asList(elts) + " has not 3 elements");
            int id = Integer.parseInt(elts[0]);
            if (synsets.size() != id)
                throw new IllegalArgumentException("synset id should be consecutive");
            synsets.add(new Synset(id, elts[1]));
        }
        in.close();
        return synsets;
    }

    // ~E
    private static Digraph readDg(String hypernymsFile, int size) throws NumberFormatException {
        Digraph res = new Digraph(size);
        In in = new In(hypernymsFile);
        while (in.hasNextLine()) {
            String elts[] = in.readLine().split(",");
            int hypoId = Integer.parseInt(elts[0]);
            for (int i = 1; i < elts.length; i++) {
                int hyperId = Integer.parseInt(elts[i]);
                res.addEdge(hypoId, hyperId);
            }
        }
        in.close();
        return res;
    }
    // ~V

    private static int getOnlyRoot(Digraph dg, int max) throws IllegalArgumentException {
        int root = -1;
        for (int i = 0; i < max; i++) {
            if (((Bag) dg.adj(i)).isEmpty()) {
                if (root != -1)
                    throw new IllegalArgumentException("multiple roots");
                root = i;
            }
        }
        if (root == -1) throw new IllegalArgumentException("no root");
        return root;
    }
    private final static int UNKNOWN = -1;
    private final static int DISCOVERED = -2;

// ~V
    private void detectCycles(Digraph dg, int root) {
        int ages[] = new int[dg.V()];
        Arrays.fill(ages, -1);
        detectCycles(dg, ages, root, 0);
    }

    private void detectCycles(Digraph dg, int[] ages, int node, int age) {
        ages[node] = DISCOVERED;
        for (Integer child : dg.adj(node)) {
            if (ages[child] == UNKNOWN) {
            } else if (ages[child] == DISCOVERED) {
                //backedge
                System.out.println("node:" + node);
                throw new IllegalArgumentException("cycle detected win wordnet because of edge " + node + "(age:" + age + ")->" + child + "(age:" + ages[child] + ")");
            } else {
                //Cross edge of foward edge
            }
        }
        ages[node] = age;
    }

    private static HashMap<String, Integer> indexWords(List<Synset> synsets) {
        final HashMap<String, Integer> words = new HashMap<String, Integer>();
        int wordId = synsets.size();
        for (Synset s : synsets) {
            for (String word : s.words) {
                if (words.containsKey(word) == false) {
                    words.put(word, wordId++);
                }
            }
        }
        return words;
    }

    private static class Synset {

        private final int id;
        private final String title;
        private final String words[];

        private Synset(int id, String title) {
            this.id = id;
            this.title = title;
            this.words = title.split(" ");
        }

    }

    // the set of nouns (no duplicates), returned as an Iterable
    public Iterable<String> nouns() {
        return Collections.unmodifiableSet(words.keySet());
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return words.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        final Integer a = words.get(nounA);
        final Integer b = words.get(nounB);
        if (a == null || b == null)
            throw new IllegalArgumentException("unknown word");
        return Math.max(0, sap.length(a, b) - 2);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        final Integer a = words.get(nounA), b = words.get(nounB);
        if (a == null || b == null)
            throw new IllegalArgumentException("unknown word");
        int ancestor = sap.ancestor(a, b);
        if (ancestor < 0) throw new IllegalArgumentException("no path");
        if (ancestor >= synsets.size()){
            ancestor=dg.adj(ancestor).iterator().next();
        }
        return synsets.get(ancestor).title;
    }

    // for unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
