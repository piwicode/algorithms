import java.util.NoSuchElementException;

/*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 2: Randomized Queues and Deques
 */
/**
 * Reservoir
 *
 * @author Pierre
 */
public class Subset {

    public static void main(String args[]) {
        if (args.length != 1) {
            throw new IllegalArgumentException();
        }
        String[] result = new String[Integer.parseInt(args[0])];
        int count = 0;
        try {
            while (true) {
                String s = StdIn.readString();

                int rnd = StdRandom.uniform(0, count + 1);
                if (count < result.length) {
                    result[count] = result[rnd];
                    result[rnd] = s;
                } else if (rnd < result.length) {
                    result[rnd] = s;
                }
                count++;
            }
        } catch (NoSuchElementException e) {//Nothing
        }
        for (String s : result) {
            StdOut.println(s);
        }
    }
}
