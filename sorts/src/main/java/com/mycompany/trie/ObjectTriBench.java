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
class ObjectTriBench extends SetBench {

    ObjectTri set;

    @Override
    public void prepare() {
        super.prepare();
        set = ObjectTri.fromList(words);
    }

    @Override
    public void run() {
        for (String w : candidates) {
            set.contains(w);
        }
    }

}
