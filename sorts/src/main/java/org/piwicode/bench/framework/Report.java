/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.piwicode.bench.framework;

/**
 *
 * @author Pierre
 */
public interface Report {

    public void write(String name, Object value);

    public void nextRaw();
}
