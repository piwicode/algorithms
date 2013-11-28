import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * construct an empty set of points
 */
public class KdTree {

    private boolean goLeft(int depth, Point2D p, Node n) {
        return (depth & 1) == 0 ? p.x() < n.p.x() : p.y() < n.p.y();
    }

    private static class Node {

        Point2D p;
        Node l, r;

        public Node(Point2D p, Node l, Node r) {
            this.p = p;
            this.l = l;
            this.r = r;
        }
    }
    private int size;
    private Node root;

    /**
     * is the set empty?
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * number of points in the set
     */
    public int size() {
        return size;
    }

    /**
     * add the point p to the set (if it is not already in the set)
     */
    public void insert(Point2D p) {
        root = insert(root, 0, p);
    }

    private Node insert(Node n, int depth, Point2D p) {
        if (n == null) {
            size++;
            return new Node(p, null, null);
        }
        if (p.equals(n.p)) {
            return n;
        }
        if (goLeft(depth, p, n)) {
            n.l = insert(n.l, depth + 1, p);
        } else {
            n.r = insert(n.r, depth + 1, p);
        }
        return n;
    }

    /**
     * does the set contain the point p?
     */
    public boolean contains(Point2D p) {
        return contains(root, 0, p);
    }

    private boolean contains(Node n, int depth, Point2D p) {
        if (n == null) {
            return false;
        }
        if (p.equals(n.p)) {
            return true;
        }
        return contains(goLeft(depth, p, n) ? n.l : n.r, depth + 1, p);
    }

    /**
     * draw all of the points to standard draw
     */
    public void draw() {
        draw(root);
    }

    private void draw(Node n) {
        if (n == null) {
            return;
        }
        n.p.draw();
        draw(n.l);
        draw(n.r);
    }

    /**
     * all points in the set that are inside the rectangle
     */
    public Iterable<Point2D> range(RectHV rect) {
        final List<Point2D> res = new ArrayList<Point2D>();
        range(root, 0, rect, res);
        return res;
    }

    private void range(Node n, int depth, RectHV rect, List<Point2D> res) {
        if (n == null) {
            return;
        }
        if (rect.contains(n.p)) {
            res.add(n.p);
        }

        if ((depth & 1) == 0) {
            if (rect.xmin() < n.p.x()) {
                range(n.l, depth + 1, rect, res);
            }
            if (rect.xmax() >= n.p.x()) {
                range(n.r, depth + 1, rect, res);
            }
        } else {
            if (rect.ymin() < n.p.y()) {
                range(n.l, depth + 1, rect, res);
            }
            if (rect.ymax() >= n.p.y()) {
                range(n.r, depth + 1, rect, res);
            }
        }
    }

    /**
     * a nearest neighbor in the set to p; null if set is empty
     */
    public Point2D nearest(Point2D p) {
        //StdDraw.clear();
        //StdDraw.setScale(0., 1.);
        //p.draw();

        RectHV r = new RectHV(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        // r = new RectHV(0,0,1,1);
        Min min = new Min(p);
        nearest(root, min, 0, r);
        return min.p;
    }

    private static class Min {

        public Min(Point2D center) {
            this.center = center;
        }

        final Point2D center;
        double d = Double.POSITIVE_INFINITY;
        Point2D p;

        void setIfNearest(Point2D candidate) {
            double curd = center.distanceSquaredTo(candidate);
            if (curd < d) {
                //StdDraw.setPenColor(Color.yellow);
                //center.drawTo(candidate);
                p = candidate;
                d = curd;
            }
        }
    }

    private void nearest(Node n, Min min, int depth, RectHV r) {
        if (n == null) {
            return;
        }
        min.setIfNearest(n.p);
        if ((depth & 1) == 0) {
            RectHV lrect = new RectHV(r.xmin(), r.ymin(), n.p.x(), r.ymax());
            RectHV rrect = new RectHV(n.p.x(), r.ymin(), r.xmax(), r.ymax());
            if (min.center.x() < n.p.x()) {
                if (lrect.distanceSquaredTo(min.center) < min.d) {
                    nearest(n.l, min, depth + 1, lrect);
                }
                if (rrect.distanceSquaredTo(min.center) < min.d) {
                    nearest(n.r, min, depth + 1, rrect);
                }
            } else {
                if (rrect.distanceSquaredTo(min.center) < min.d) {
                    nearest(n.r, min, depth + 1, rrect);
                }
                if (lrect.distanceSquaredTo(min.center) < min.d) {
                    nearest(n.l, min, depth + 1, lrect);
                }
            }
        } else {
            RectHV lrect = new RectHV(r.xmin(), r.ymin(), r.xmax(), n.p.y());
            RectHV rrect = new RectHV(r.xmin(), n.p.y(), r.xmax(), r.ymax());
            if (min.center.y() < n.p.y()) {
                if (lrect.distanceSquaredTo(min.center) < min.d) {
                    nearest(n.l, min, depth + 1, lrect);
                }
                if (rrect.distanceSquaredTo(min.center) < min.d) {
                    nearest(n.r, min, depth + 1, rrect);
                }
            } else {
                if (rrect.distanceSquaredTo(min.center) < min.d) {
                    nearest(n.r, min, depth + 1, rrect);
                }
                if (lrect.distanceSquaredTo(min.center) < min.d) {
                    nearest(n.l, min, depth + 1, lrect);
                }
            }
        }
    }
}
