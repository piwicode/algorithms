/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sorts;

import static com.mycompany.sorts.SortBench.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class MergesortTest {

    @Test
    public void testSort() {
        final int array[] = new int[1000];
        shuffle(array);
        setIdentity(array);
        final int buffer[] = array.clone();
        Mergesort.sort(array, buffer, 0, array.length-1);
        assertTrue(isIdentity(array));
    }

}
