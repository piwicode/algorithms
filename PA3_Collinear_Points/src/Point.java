import java.util.Comparator;

/*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 3: Collinear Points
 */
/**
 *
 * @author Pierre
 */
public class Point implements Comparable<Point> {

    private final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {

        @Override
        public int compare(Point o1, Point o2) {
            return Double.compare(slopeTo(o1), slopeTo(o2));
        }
    };

    // plot this point to standard drawing
    public void draw() {
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // return string representation of this point
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // is this point lexicographically smaller than that point?
    @Override
    public int compareTo(Point that) {
        final int yc = Util.compare(y, that.y);
        return yc != 0 ? yc : Util.compare(x, that.x);
    }

    // the slope between this point and that point
    public double slopeTo(Point that) {
        boolean samex = x == that.x, samey = y == that.y;
        if (samex) {
            return samey ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        }
        if (samey) {
            return +0.;
        }
        return (double) (y - that.y) / (x - that.x);
    }

}
