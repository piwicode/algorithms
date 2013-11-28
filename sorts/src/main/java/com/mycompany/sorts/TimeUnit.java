/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sorts;

/**
 *
 * @author Pierre
 */
public enum TimeUnit implements Unit {
    ns,µs,ms,s,min,h;    
    private static final double[] scale={1E-9,1E-6,1E-3,1E0,60,3600};
    public double ratio() {
        return scale[ordinal()];
    }    
    public static String format(double v){
        return Units.format(v, TimeUnit.class);
    }
}
