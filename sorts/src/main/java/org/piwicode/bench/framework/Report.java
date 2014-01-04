/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
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
