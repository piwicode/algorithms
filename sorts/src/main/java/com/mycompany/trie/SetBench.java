/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trie;

import com.google.common.base.Charsets;
import com.google.common.collect.Sets;
import com.google.common.io.CharStreams;
import com.google.common.io.Resources;
import com.mycompany.sorts.MacroBench;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Pierre
 */
abstract class SetBench implements MacroBench {

    String[] candidates;
    String[] words;

    public SetBench() {
    }

    @Override
    public void prepare() {
        try {
            final URL url = Resources.getResource(SetBench.class, "dictionary.txt");
            final List<String> wordsLst = CharStreams.readLines(Resources.asCharSource(url, Charsets.UTF_8));
            final List<String> candidatesLst = new ArrayList<>();
            Random rnd = new Random();
            for (String w : wordsLst) {
                final int p = rnd.nextInt(w.length());
                candidatesLst.add(w.substring(0, p) + Character.toUpperCase(w.charAt(p)) + w.substring(p + 1, w.length()));
                candidatesLst.add(new String(w.toCharArray()));
            }            
            Collections.shuffle(wordsLst);
            Collections.shuffle(candidatesLst);
            candidates = candidatesLst.toArray(new String[0]);
            words = wordsLst.toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
