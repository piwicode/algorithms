/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trie;

import java.util.HashSet;

/**
 *
 * @author Pierre
 */
class HashSetBench extends SetBench {

    HashSet<String> set;

    @Override
    public void prepare() {
        super.prepare();         
        set = new HashSet<>(words);
    }

    @Override
    public void run() {
        for (String w : candidates) {
            set.contains(w);
        }
    }

}
