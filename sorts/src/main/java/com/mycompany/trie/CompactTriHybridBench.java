/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trie;

/**
 *
 * @author Pierre
 */
public class CompactTriHybridBench extends SetBench {

    CompactTriHybrid set;
    int split = 16;

    public void setSplit(int insertLength) {
        this.split = insertLength;
    }

    @Override
    public void prepare() {
        super.prepare();
        set = ObjectTri.fromList(words).toCompactTriHybrid(split);
    }

    @Override
    public void run() {
        for (String w : candidates) {
            set.contains(w);
        }
    }

}
