/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sorts;

import java.util.Random;

/**
 *
 * @author Pierre
 */
public abstract class SortBench implements MacroBench {

    int[] array;
    int N = 1000 * 1000;
    double P = 0.;

    public void setN(int N) {
        this.N = N;
    }

    public void setP(double P) {
        this.P = P;
    }

    @Override
    public void prepare() {
        array = new int[N];
        assign(array, P);
        shuffle(array);
    }

    static void assign(int[] array, double P) {
        int M = (int) ((1. - P) * array.length + 1);
        for (int i = 0; i < array.length; i++) {
            array[i] = i % M;
        }
    }

    static int[] setIdentity(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return array;
    }

    static boolean isIdentity(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != i) {
                return false;
            }
        }
        return true;
    }

    static void shuffle(int[] array) {
        final Random rnd = new Random();
        for (int i = 1; i < array.length; i++) {
            int r = rnd.nextInt(i + 1);
            int t = array[i];
            array[i] = array[r];
            array[r] = t;
        }
    }

}
