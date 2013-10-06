import java.awt.Color;

/*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 3: Collinear Points
 */
/**
 *
 * @author Pierre
 */
public class Util {

    static int compare(int x, int y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

  

    static Point[] parse(String fn) {
        final In in = new In(fn);
        final int nbPt = in.readInt();
        final Point[] points = new Point[nbPt];
        for (int i = 0; i < nbPt; i++) {
            points[i] = new Point(in.readInt(), in.readInt());
        }
        return points;
    }

    static void initDraw(Point[] points) {
        StdDraw.clear();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.setPenColor(Color.BLUE);
    }
}
