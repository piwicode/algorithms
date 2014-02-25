/*
 *  Simple preformance benchmark and algorythms try-out:
 *  https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.dictionary;

import java.util.BitSet;
import java.util.Random;
import org.piwicode.bench.framework.MacroBench;

/**
 *
 * @author Pierre
 */
public class BitsetBench implements MacroBench {

    Integer[] array;
    int N = 1000 * 1000;
    
    public void setN(int N) {
        this.N = N;
    }
    
    @Override
    public void prepare() {
        array = new Integer[N];
        Random r = new Random();
        for(int i = 0 ; i < array.length; i++){
            array[i]=r.nextInt(N);
        }        
    }

    @Override
    public void run() {
        BitSet set = new BitSet(N);
        for(int i = 0 ; i < array.length ; i++){
            set.set(array[i]);
        }
    }

}
