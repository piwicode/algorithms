/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sorts;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pierre
 */
public class MainDupplicate {

    public static void main(String[] args) {
        MacroBench[] benches = {new Quicksort(),
            new QuicksortWithTailRecursion(),
            new Mergesort(),
            new DualpivotQuicksort(),
            new Heapsort(),
            new HybridQuicksort(),
            new RadixCountingSort(),
            new RadixBinarySort()};
        final List<Session> sessions = new ArrayList<>();
        for (MacroBench bench : benches) {
            for (double p = 0.; p < 1.; p+=.1) {
                sessions.add(new Session(bench).with("p", p));
            }
        }
        
        //Warming up
        Session.runAll(sessions, 1);
        //Bench
        Session.runAll(sessions, 5);

        // Summary        
        System.out.println("------------------------------------------------------------------------");
        Report.writeAllAsCsv(sessions);
    }
}
