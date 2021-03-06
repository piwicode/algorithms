/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.framework;

import java.util.ArrayList;

/**
 *
 * @author Pierre
 */
class PrettyPrint {

    public static void printAll(Iterable<?> objs, String separator) {
        ArrayList<Integer> colSize = new ArrayList<Integer>();
        ArrayList<String[]> rows = new ArrayList<String[]>();
        for (Object obj : objs) {
            String[] cols = obj.toString().split(separator);
            rows.add(cols);
            for (int i = 0; i < colSize.size() && i < cols.length; i++) {
                if (cols[i].length() > colSize.get(i)) {
                    colSize.set(i, cols[i].length());
                }
            }
            for (int i = colSize.size(); i < cols.length; i++) {
                colSize.add(cols[i].length());
            }
        }
        for (String[] cols : rows) {
            for (int i = 0; i < cols.length; i++) {
                if (i != 0) {
                    System.out.print(separator);
                }
                System.out.print(cols[i]);
                for (int j = cols[i].length(); j < colSize.get(i); j++) {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}
