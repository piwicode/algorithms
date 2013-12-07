/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.piwicode.bench.searches;

import org.piwicode.bench.framework.Result;
import org.piwicode.bench.framework.Session;

/**
 *
 * @author Pierre
 */
public class Main {

    public static void main(String[] args) {
        Session session = Session.create();
        session.let("class").beOneOf(BinarySearchBench.class, LinearSearchBench.class);
        session.let("n").beInRange(1, 100);
        session.let("r").beEqualTo(500);
        
        Result run = session.run(200, 1000);
        run.plot("Average elapsed time (µs)", "class", "n", "mean");
        
    }
}
