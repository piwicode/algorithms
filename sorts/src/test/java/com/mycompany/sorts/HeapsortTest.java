/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sorts;

import static com.mycompany.sorts.SortBench.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Pierre
 */
public class HeapsortTest {

    @Test
    public void testSort() {
        final int array[] = new int[1000];
        shuffle(array);
        setIdentity(array);
        Heapsort.sort(array);
        assertTrue(isIdentity(array));
    }
}
