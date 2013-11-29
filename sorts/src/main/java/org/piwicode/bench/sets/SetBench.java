/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.sets;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.piwicode.bench.framework.MacroBench;

/**
 *
 * @author Pierre
 */
abstract class SetBench implements MacroBench {

    String[] candidates;
    List<String> words;

    public SetBench() {
    }

    @Override
    public void prepare() {
        try {
            final URL url = Resources.getResource(SetBench.class, "dictionary.txt");
            List<String> lines = CharStreams.readLines(Resources.asCharSource(url, Charsets.UTF_8));
            Collections.shuffle(lines);
            //lines=lines.subList(0, 500);            
            candidates = lines.toArray(new String[0]);
            words = new ArrayList<>(lines.size() / 2);
            for (int i = 0; i < candidates.length / 2; i++) {
                words.add(new String(candidates[i].toCharArray()));                
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
