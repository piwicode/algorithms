package smr604;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Pierre
 */
class Walker {
    private final String txt;
    private final Matcher m;

    public Walker(String txt) {
        this.txt = txt;
        m = Pattern.compile("").matcher(txt);
    }

    Walker skip(Pattern pattern) {
        next(pattern);
        return this;
    }

    boolean hasNext(Pattern pattern) {
        return m.usePattern(pattern).lookingAt();
    }

    String next(Pattern pattern) {
        if (!hasNext(pattern))
            throw new IllegalStateException("Expects " + pattern + " but have " + txt.substring(m.regionStart(), Math.min(txt.length(), m.regionStart() + 30)));
        final String result = m.group();
        consume();
        return result;
    }

    Walker consume() {
        m.region(m.end(), m.regionEnd());
        return this;
    }

}
