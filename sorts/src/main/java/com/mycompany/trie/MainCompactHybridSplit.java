/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trie;

import com.mycompany.sorts.HybridQuicksort;
import com.mycompany.sorts.PrettyPrint;
import com.mycompany.sorts.Report;
import com.mycompany.sorts.Session;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Pierre
 */
public class MainCompactHybridSplit {

    public static void main(String[] args) {
        final CompactTriHybridBench bench = new CompactTriHybridBench();
        final List<Session> sessions = new ArrayList<>();
        for (int X = 0; X < 200; X += 1) {
            sessions.add(new Session(bench).with("split", X));
        }
        Collections.shuffle(sessions);
        //Warming up
        Session.runAll(sessions, 2);
        //Bench
        Session.runAll(sessions, 8);

        Collections.sort(sessions);
        System.out.println("------------------------------------------------------------------------");
        Report.writeAllAsCsv(sessions);
    }
}
