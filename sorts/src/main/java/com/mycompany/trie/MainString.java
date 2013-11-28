/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trie;

import com.mycompany.sorts.PrettyPrint;
import com.mycompany.sorts.Session;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Pierre
 */
public class MainString {

    public static void main(String[] args) {

        final List<Session> sessions = new ArrayList<>();
        sessions.add(new Session(new CompactTriBench()));
        //sessions.add(new Session(new ObjectTriBench()));
        //sessions.add(new Session(new HashSetBench()));
        //sessions.add(new Session(new ImmutableHashSetBench()));
        //Warming up
        Session.runAll(sessions, 5);
        //Bench
        Session.runAll(sessions, 50);

        // Summary
        Collections.sort(sessions);
        System.out.println("------------------------------------------------------------------------");
        System.out.println("test presence for " + 80368 * 2 + " words of " + (622783 / 80368 - 1) + " chars with 50% matching a value in the set");
        PrettyPrint.printAll(sessions, "-");
    }
}
