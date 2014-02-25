/*
 *  Simple preformance benchmark and algorythms try-out:
 *  https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.wordsets;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author plabatut
 */
public class Dictionary {

    public static List<String> lines;

    static {
        try {
            final URL url = Resources.getResource(Dictionary.class, "dictionary.txt");
            lines = CharStreams.readLines(Resources.asCharSource(url, Charsets.UTF_8));
            Collections.shuffle(lines, new Random(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] copy() {
        return lines.toArray(new String[0]);
    }

    public static String[] sample(int size) {
        final String[] array = new String[size];
        final Random rnd = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = lines.get(rnd.nextInt(lines.size()));
        }
        return array;
    }
}
