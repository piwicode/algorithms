/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.wordsets;

/**
 *
 * @author Pierre
 */
public class CompactTriHybridBench extends SetBench {

    CompactTriHybrid set;
    int split = 16;

    public void setSplit(int insertLength) {
        this.split = insertLength;
    }

    @Override
    public void prepare() {
        super.prepare();
        set = ObjectTri.fromList(words).toCompactTriHybrid(split);
    }

    @Override
    public void run() {
        for (String w : candidates) {
            set.contains(w);
        }
    }

}
