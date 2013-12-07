/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.piwicode.bench.framework;

import java.util.Random;

/**
 *
 * @author Pierre
 */
public class IntegerArray {

    public static int[] shuffle(int[] array) {
        final Random rnd = new Random();
        for (int i = 1; i < array.length; i++) {
            int r = rnd.nextInt(i + 1);
            int t = array[i];
            array[i] = array[r];
            array[r] = t;
        }
        return array;
    }

    static int[] setIdentity(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return array;
    }

    public static boolean isIdentity(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != i) {
                return false;
            }
        }
        return true;
    }

    public static int[] shuffeledIdentity(int N) {
        return shuffle(setIdentity(new int[N]));
    }

    public static int[] identity(int N) {
        return setIdentity(new int[N]);
    }

}
