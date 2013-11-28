/*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 3: Collinear Points
 */
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Pierre
 */
public class RandTest {

    Random rnd = new Random(0);
    PointSET r = new PointSET();
    KdTree t = new KdTree();

    @Test
    public void rndNearest() {
        buildRandom();
        for (int i = 0; i < 10000; i++) {
            Point2D p = new Point2D(rnd.nextDouble(), rnd.nextDouble());
            Assert.assertEquals(r.nearest(p), t.nearest(p));
        }
    }

    @Test
    public void rndRange() {
        buildRandom();
        for (int i = 0; i < 10000; i++) {
            double a = rnd.nextDouble(), b = rnd.nextDouble();
            double c = rnd.nextDouble(), d = rnd.nextDouble();
            RectHV rec = new RectHV(Math.min(a, b), Math.min(c, d), Math.max(a, b), Math.max(c, d));
            final List<Point2D> tres = (List<Point2D>) t.range(rec);
            Collections.sort(tres);
            final List<Point2D> rres = (List<Point2D>) r.range(rec);
            Collections.sort(rres);
            Assert.assertEquals(rres, tres);
        }
    }

    private void buildRandom() {
        for (int i = 0; i < 5; i++) {
            Point2D p = new Point2D(rnd.nextDouble(), rnd.nextDouble());
            r.insert(p);
            t.insert(p);
        }
        Assert.assertEquals(r.size(), t.size());
    }

}
