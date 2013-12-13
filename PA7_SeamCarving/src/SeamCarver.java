import java.awt.Color;

/**
 * Solution proposal to coursera Algorithms Part 2
 *
 * Programming Assignment 2: Seam Carving
 */
public class SeamCarver {

    private int w, h;
    private int[] buf;
    private double[] d;
    private int[] parent;

    public SeamCarver(Picture picture) {
        if (picture == null) throw new IllegalArgumentException();
        w = picture.width();
        h = picture.height();
        buf = new int[w * h];
        d = new double[w * h];
        parent = new int[w * h];
        for (int y = 0, p = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                buf[p++] = picture.get(x, y).getRGB();
            }
        }
    }

    /**
     * current picture
     */
    public Picture picture() {
        final Picture picture = new Picture(w, h);
        int p = 0;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                picture.set(x, y, new Color(buf[p++]));
            }
        }
        return picture;
    }

    /**
     * width of current picture
     */
    public int width() {
        return w;
    }

    /**
     * height of current picture
     */
    public int height() {
        return h;
    }

    /**
     * energy of pixel at column x and row y in current picture
     */
    public double energy(int x, int y) {
        if (x < 0 || y < 0 || x >= w || y >= h) {
            throw new IndexOutOfBoundsException("("+x+","+y+")");
        }
        if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
            return 195075;
        }
        int p = x + y * w;
        return delta2(buf[p - w], buf[p + w]) + delta2(buf[p - 1], buf[p + 1]);
    }

    private int delta2(int u, int v) {
        int dr = (u & 0xff) - (v & 0xff);
        u >>= 8;
        v >>= 8;
        int dg = (u & 0xff) - (v & 0xff);
        u >>= 8;
        v >>= 8;
        int db = (u & 0xff) - (v & 0xff);
        return dr * dr + dg * dg + db * db;
    }

    /**
     * sequence of indices for horizontal seam in current picture
     */
    public int[] findHorizontalSeam() {
        for (int y = 0, p = 0; y < h; y++, p += w) {
            d[p] = energy(0, y);
        }
        for (int x = 1; x < w; x++) {
            for (int y = 0, p = x; y < h; y++, p += w) {
                int min = p - 1, p7 = p - 1 - w, p2 = p - 1 + w;
                if (y != 0 && d[min] >= d[p7]) min = p7;
                if (y != h - 1 && d[min] > d[p2]) min = p2;
                parent[p] = min;
                d[p] = d[min] + energy(x, y);
            }
        }
        //Find minimum of last row
        int from = w - 1, cur = from;
        for (int p = from, to = w * h - 1; p < to; p += w) {
            if (d[cur] > d[p]) cur = p;
        }
        //Crawl up
        int result[] = new int[w];
        for (int p = w - 1; p >= 0; p--) {
            result[p] = cur / w;
            cur = parent[cur];
        }
        return result;
    }

    /**
     * sequence of indices for vertical seam in current picture
     */
    public int[] findVerticalSeam() {
        for (int x = 0; x < w; x++) {
            d[x] = energy(x, 0);
        }
        for (int y = 1, p = w; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int min = p - w, p7 = min - 1, p9 = min + 1;
                if (x != 0 && d[min] >= d[p7]) min = p7;
                if (x != w - 1 && d[min] > d[p9]) min = p9;
                parent[p] = min;
                d[p] = d[parent[p]] + energy(x, y);
                p++;
            }
        }
        //Find minimum of last row
        int from = w * (h - 1), cur = from;
        for (int p = from, to = from + w; p < to; p++) {
            if (d[cur] > d[p]) cur = p;
        }
        //Crawl up
        int result[] = new int[h];
        for (int p = h - 1, offset = from; p >= 0; offset -= w, p--) {
            result[p] = cur - offset;
            cur = parent[cur];
        }
        return result;
    }

    /**
     * remove horizontal seam from current picture
     */
    public void removeHorizontalSeam(int[] a) {
        if (a.length != w) throw new IllegalArgumentException();
        for (int i = 0; i < a.length; i++) {
            if (a[i] < 0 || a[i] >= h) throw new IllegalArgumentException();
            if (i != 0 && Math.abs(a[i] - a[i - 1]) > 1)
                throw new IllegalArgumentException();
        }
        if (a.length != w) throw new IllegalArgumentException();
        for (int x = 0; x < w; x++) {
            int p = x + a[x] * w;
            for (int y = a[x] + 1; y < h; y++, p += w) {
                buf[p] = buf[p + w];
            }
        }
        h--;
    }

    /**
     * remove vertical seam from current picture
     */
    public void removeVerticalSeam(int[] a) {
        if (a.length != h) throw new IllegalArgumentException();
        for (int i = 0; i < a.length; i++) {
            if (a[i] < 0 || a[i] >= w) throw new IllegalArgumentException();
            if (i != 0 && Math.abs(a[i] - a[i - 1]) > 1)
                throw new IllegalArgumentException();
        }
        int p = 0, q = 0;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < a[y]; x++) {
                buf[p++] = buf[q++];
            }
            q++;
            for (int x = a[y] + 1; x < w; x++) {
                buf[p++] = buf[q++];
            }
        }
        w--;
        assert p == w * h;
    }

}
