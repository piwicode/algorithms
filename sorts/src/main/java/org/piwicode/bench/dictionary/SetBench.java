/*
 *  Simple preformance benchmark and algorythms try-out:
 *  https://github.com/piwicode/algorithms
 */

package org.piwicode.bench.dictionary;

import java.util.Random;
import org.piwicode.bench.framework.MacroBench;

/**
 *
 * @author Pierre
 */
public class SetBench implements MacroBench{
    Integer[] array;
    int N = 1000 * 1000;

    public void setN(int N) {
        this.N = N;
    }

    @Override
    public void prepare() {
        array = new Integer[N];
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(100000) - 50000;
        }
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
