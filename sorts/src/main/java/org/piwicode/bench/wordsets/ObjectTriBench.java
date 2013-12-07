/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.wordsets;
/**
 *
 * @author Pierre
 */
class ObjectTriBench extends SetBench {

    ObjectTri set;

    @Override
    public void prepare() {
        super.prepare();
        set = ObjectTri.fromList(words);
    }

    @Override
    public void run() {
        for (String w : candidates) {
            set.contains(w);
        }
    }

}
