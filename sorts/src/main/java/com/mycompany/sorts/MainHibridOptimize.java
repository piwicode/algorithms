/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sorts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Pierre
 */
public class MainHibridOptimize {

    public static void main(String[] args) {
        final HybridQuicksort sort = new HybridQuicksort();
        final List<Session> sessions = new ArrayList<>();
        for (int X = 2; X < 300; X++) {
            sort.setSplit(X);
            sessions.add(new Session(sort).with("split", X));
        }
        Collections.shuffle(sessions);
        Report report = new Report();
        //Warming up
        Session.runAll(sessions, 2);
        //Bench
        Session.runAll(sessions, 2);

        Collections.sort(sessions);
        System.out.println("------------------------------------------------------------------------");
        PrettyPrint.printAll(sessions, "-");
    }
}
