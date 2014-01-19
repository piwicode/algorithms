package smr604;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Pierre
 */
public class PowerOfThreeRef {

    public static String ableToGet(int tx, int ty) {
        Pos t = new Pos(tx, ty);
        Set<Pos> p1 = new HashSet<Pos>();
        Set<Pos> p2 = new HashSet<Pos>();
        p1.add(new Pos(0, 0));

        for (long kp3 = 1;; kp3 *= 3) {
            p2.clear();
            for (Pos p : p1) {
                if (p.x != tx) {
                    if (Math.abs(p.x - tx) < kp3) continue;
                } else if (p.y != ty) {
                    if (Math.abs(p.y - ty) < kp3) continue;
                } else
                    return "Possible";

                p2.add(new Pos(p.x - kp3, p.y));
                p2.add(new Pos(p.x + kp3, p.y));
                p2.add(new Pos(p.x, p.y - kp3));
                p2.add(new Pos(p.x, p.y + kp3));
            }
            if (p2.isEmpty()) return "Impossible";
            Set<Pos> pt = p1;
            p1 = p2;
            p2 = pt;
        }
    }

    static class Pos {

        long x, y;

        public Pos(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 83 * hash + (int) (this.x ^ (this.x >>> 32));
            hash = 83 * hash + (int) (this.y ^ (this.y >>> 32));
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            final Pos other = (Pos) obj;
            if (this.x != other.x) return false;
            if (this.y != other.y) return false;
            return true;
        }

        @Override
        public String toString() {
            return "Pos{" + "x=" + x + ", y=" + y + '}';
        }

    }

}
