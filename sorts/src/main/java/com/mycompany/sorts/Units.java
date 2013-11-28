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
public class Units {

    static String format(double v, Class<? extends Enum<? extends Unit>> unit) {
        final Unit[] scale= (Unit[]) unit.getEnumConstants();
        Unit d = scale[0];
        for (int i = 0; i < scale.length - 1; i++) {
            d = scale[i];
            if (v < scale[i + 1].ratio()) {
                break;
            }
        }
        return String.format("%.2f " + d, v / d.ratio());
    }
}
