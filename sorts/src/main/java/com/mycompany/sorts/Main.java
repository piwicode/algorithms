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
public class Main {

    public static void main(String[] args) {

        final List<Session> sessions = new ArrayList<>();
        sessions.add(new Session(new Quicksort()));
        sessions.add(new Session(new QuicksortWithTailRecursion()));
        sessions.add(new Session(new Mergesort()));
        sessions.add(new Session(new DualpivotQuicksort()));
        sessions.add(new Session(new Heapsort()));
        sessions.add(new Session(new HybridQuicksort()));
        sessions.add(new Session(new RadixCountingSort()));
        sessions.add(new Session(new RadixBinarySort()));
        
        //Warming up
        Session.runAll(sessions, 5);
        //Bench
        Session.runAll(sessions, 20);

        // Summary
        Collections.sort(sessions);
        System.out.println("------------------------------------------------------------------------");
        PrettyPrint.printAll(sessions, "-");
    }
}
