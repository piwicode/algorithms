/*
 * Solution proposal to TopCoder Single Round Match
 */
package cup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Pierre
 */
public class Regex {
    static class Vertex {

        public Vertex(char c) {
            this.c = c;
        }

        boolean canEat(char in) {
            if (c == '.') return true;
            if (c == '*') return false;
            return in == c;
        }
        char c;
        List<Integer> epsilon = new ArrayList<>();
        List<Integer> next = new ArrayList<>();

    }

    public static int countMatch(String sregex, String stxt) {
        final Vertex[] v = createGraph(sregex.toCharArray());
        final char[] txt = stxt.toCharArray();
        int[] cnt1 = new int[v.length];
        int[] cnt2 = new int[v.length];
        int total = 0;
        for (int i = 0; i < txt.length; i++) {
            cnt1[0] = 1;
            for (int vidx = 0; vidx < cnt1.length; vidx++) {
                if (cnt1[vidx] != 0 && v[vidx].canEat(txt[i]))
                    for (int to : v[vidx].next) {
                        cnt2[to] += cnt1[vidx];
                    }
            }
            Arrays.fill(cnt1, 0);
            for (int vidx = 0; vidx < cnt1.length; vidx++) {
                cnt1[vidx] += cnt2[vidx];
                for (int e : v[vidx].epsilon) {
                    cnt1[e] += cnt2[vidx];
                }
            }
            Arrays.fill(cnt2, 0);
            total += cnt1[cnt1.length - 1];
            
            System.out.println("eat: "+txt[i]);
            System.out.println(sregex);
            
            for(int vidx = 0 ; vidx < cnt1.length; vidx++)
                System.out.print(cnt1[vidx]);
            
            System.out.println();
        }
        return total;
    }

    public static Vertex[] createGraph(char[] regex) {
        Vertex[] v = new Vertex[regex.length + 1];
        for (int i = 0; i < regex.length; i++) {
            v[i] = new Vertex(regex[i]);
            final char c = regex[i];
            if (c == '*') {
                v[i - 1].epsilon.add(i + 1);
                v[i].epsilon.add(i - 1);
                v[i].epsilon.add(i + 1);
            } else
                v[i].next.add(i + 1);
        }
        v[v.length - 1] = new Vertex('?');
        return v;
    }
}
