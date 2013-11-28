/*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 3: Collinear Points
 */

/**
 *
 * @author Pierre
 */
public interface PointTree {

    /**
     * does the set contain the point p?
     */
    boolean contains(Point2D p);

    /**
     * add the point p to the set (if it is not already in the set)
     */
    void insert(Point2D p);

    /**
     * is the set empty?
     */
    boolean isEmpty();

    /**
     * a nearest neighbor in the set to p; null if set is empty
     */
    Point2D nearest(Point2D p);

    /**
     * all points in the set that are inside the rectangle
     */
    Iterable<Point2D> range(RectHV rect);

    /**
     * number of points in the set
     */
    int size();
    
}
