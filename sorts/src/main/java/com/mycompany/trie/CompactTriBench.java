/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trie;

import com.mycompany.sorts.MacroBench;
import java.util.Arrays;

/**
 *
 * @author Pierre
 */
class CompactTriBench extends SetBench {

    CompactTri set;

    @Override
    public void prepare() {
        super.prepare();
        set = ObjectTri.fromList(words).toCompactTri();        
    }

    @Override
    public void run() {
        for (String w : candidates) {
            set.contains(w);
        }
    }

}
