/*
 * Solution proposal to coursera Algorithms Part 1
 * Programming Assignment 3: Collinear Points
 */
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Pierre
 */
@RunWith(Parameterized.class)
public class PointSETTest {

    static class KdTreeT extends KdTree implements PointTree {
    }

    static class PointSETT extends PointSET implements PointTree {
    }

    final PointTree tree;

    public PointSETTest(Class clz) throws InstantiationException, IllegalAccessException {
        tree = (PointTree) clz.newInstance();
    }

    @Parameterized.Parameters(name = "{index} - {0}")
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{{PointSETT.class}, {KdTreeT.class}});
    }

    @Test
    public void testEmpty() {
        Assert.assertTrue(tree.isEmpty());
        Assert.assertEquals(0, tree.size());
        Assert.assertFalse(tree.contains(new Point2D(0, 0)));
        Assert.assertNull(tree.nearest(new Point2D(0, 0)));
        Iterable<Point2D> r = tree.range(new RectHV(0, 0, 1, 1));
        Assert.assertFalse(r.iterator().hasNext());
    }

    @Test
    public void testInsert1() {
        tree.insert(new Point2D(0, 0));
        Assert.assertTrue(tree.contains(new Point2D(0, 0)));
        Assert.assertFalse(tree.contains(new Point2D(1, 0)));
        Assert.assertFalse(tree.isEmpty());
        Assert.assertEquals(1, tree.size());
        ArrayList<Point2D> ref = new ArrayList<Point2D>();
        ref.add(new Point2D(0, 0));
        Assert.assertEquals(ref, tree.range(new RectHV(0, 0, 1, 1)));
        Assert.assertEquals(ref, tree.range(new RectHV(-1, 0, 0, 1)));
        Assert.assertEquals(ref, tree.range(new RectHV(0, -1, 1, 0)));
        Assert.assertEquals(ref, tree.range(new RectHV(-1, -1, 0, 0)));
        Assert.assertEquals(new Point2D(0, 0), tree.nearest(new Point2D(0, 0)));
        Assert.assertEquals(new Point2D(0, 0), tree.nearest(new Point2D(1, 1)));
    }

    @Test
    public void doubleInsert() {
        tree.insert(new Point2D(0, 0));
        Assert.assertFalse(tree.isEmpty());
        Assert.assertEquals(1, tree.size());
    }

    @Test
    public void multiInsert() {
        tree.insert(new Point2D(0, 0));
        tree.insert(new Point2D(1, 0));
        tree.insert(new Point2D(0, 1));
        tree.insert(new Point2D(1, 1));
        Assert.assertFalse(tree.isEmpty());
        Assert.assertEquals(4, tree.size());
    }

    @Test
    public void circle10() {
        load("circle10.txt");
        Assert.assertEquals(new Point2D(0.975528, 0.345492), tree.nearest(new Point2D(0.81, 0.3)));
    }

    private void load(String fileName) {
        URL url = getClass().getResource(fileName);
        final In in = new In(url);
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            tree.insert(new Point2D(x, y));
        }
        in.close();
    }
}
