/*
 *  Simple preformance benchmark and algorythms try-out:
 *  https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.dictionary;

import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;
import org.piwicode.bench.framework.MacroBench;
import org.piwicode.bench.sorts.SortBench;

/**
 *
 * @author Pierre
 */
public class TreeBench implements MacroBench {

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
            array[i]=r.nextInt();
        }        
    }

    @Override
    public void run() {
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0 ; i < array.length ; i++){
            set.add(array[i]);
        }
    }

}
