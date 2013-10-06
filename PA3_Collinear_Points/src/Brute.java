import java.util.Arrays;

/*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 3: Collinear Points
 */
/**
 *
 * @author Pierre
 */
public class Brute {

    public static void main(String[] args) {
        new Brute().go(Util.parse(args[0]));
    }

    public void go(Point[] points) {
        Util.initDraw(points);
        Arrays.sort(points);
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                final Double s1 = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < points.length; k++) {
                    if (s1 != points[i].slopeTo(points[k])) {
                        continue;
                    }
                    for (int l = k + 1; l < points.length; l++) {
                        if (s1 != points[i].slopeTo(points[l])) {
                            continue;
                        }
                        print(points[i]);
                        print(" -> ");
                        print(points[j]);
                        print(" -> ");
                        print(points[k]);
                        print(" -> ");
                        print(points[l]);
                        print("\n");
                        points[i].drawTo(points[l]);
                    }
                }
            }
        }
    }

    void print(Object s) {
        StdOut.print(s);
    }

}
