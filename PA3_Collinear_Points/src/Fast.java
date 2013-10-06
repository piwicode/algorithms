import java.awt.Color;
import java.util.Arrays;

/*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 3: Collinear Points
 *//*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 3: Collinear Points
 */
/*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 3: Collinear Points
 *//*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 3: Collinear Points
 */


/**
 *
 * @author Pierre
 */
public class Fast {

    public static void main(String[] args) {
        new Fast().go(Util.parse(args[0]));
    }

    public void go(Point[] points) {
        Util.initDraw(points);
        Arrays.sort(points);
        final Point[] pts2 = points.clone();
        for (int refIdx = 0; refIdx < points.length; refIdx++) {
            System.arraycopy(points, 0, pts2, 0, points.length);
            final Point ref = points[refIdx];
            Arrays.sort(pts2, ref.SLOPE_ORDER);
            for (int startIdx = 0; startIdx < pts2.length;) {
                double startSlope = ref.slopeTo(pts2[startIdx]);
                int k;
                boolean ordered = true;
                for (k = startIdx + 1; k < pts2.length; k++) {
                    if (ref.slopeTo(pts2[k]) != startSlope) {
                        break;//break when k is no longer in the sequence of same slope
                    }
                    //This is an sequence that has already been reported
                    //when ref was the point that changed ordered to false
                    ordered &= ref.compareTo(pts2[k]) < 0;
                }
                //The sequence of same slope is [startIdx,k[
                if (ordered && k - startIdx >= 3) {
                    yield(pts2, ref, startIdx, k);
                }
                startIdx = k;
            }
        }
    }

    void print(Object s) {
        StdOut.print(s);
    }

    private void yield(final Point[] points, Point ref, int from, int to) {
        ref.drawTo(points[to - 1]);
        for (int i = from; i < to; i++) {
            print(" -> ");
            print(points[i]);
        }
        print("\n");
    }

}
