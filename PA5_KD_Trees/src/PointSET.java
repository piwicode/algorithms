import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * construct an empty set of points
 */
public class PointSET {

    private TreeSet<Point2D> set = new TreeSet<Point2D>();

    /**
     * is the set empty?
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * number of points in the set
     */
    public int size() {
        return set.size();
    }

    /**
     * add the point p to the set (if it is not already in the set)
     */
    public void insert(Point2D p) {
        set.add(p);
    }

    /**
     * does the set contain the point p?
     */
    public boolean contains(Point2D p) {
        return set.contains(p);
    }

    /**
     * draw all of the points to standard draw
     */
    public void draw() {
        for (Point2D p : set) {
            p.draw();
        }
    }

    /**
     * all points in the set that are inside the rectangle
     */
    public Iterable<Point2D> range(RectHV rect) {
        final List<Point2D> res = new ArrayList<Point2D>();
        for (Point2D cur : set) {
            if (rect.contains(cur)) {
                res.add(cur);
            }
        }
        return res;
    }

    /**
     * a nearest neighbor in the set to p; null if set is empty
     */
    public Point2D nearest(Point2D p) {
        if (set.isEmpty()) {
            return null;
        }
        Point2D nearest = set.first();
        double minDist = p.distanceSquaredTo(nearest);
        for (Point2D cur : set) {
            double dist = p.distanceSquaredTo(cur);
            if (dist < minDist) {
                minDist = dist;
                nearest = cur;
            }
        }
        return nearest;
    }
}
