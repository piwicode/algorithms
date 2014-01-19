package smr604;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Resources;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.runners.Parameterized.Parameter;

/**
 *
 * @author Pierre
 */
public class SystemTest {
    private static final Pattern DELIMITER = Pattern.compile("[\\s,]*");
    private static final Pattern QUOTE = Pattern.compile("\"");
    private static final Pattern NOQUOTE = Pattern.compile("[^\"]*");
    private static final Pattern OPEN = Pattern.compile("\\{");
    private static final Pattern CLOSE = Pattern.compile("}");
    private static final Pattern INT = Pattern.compile("-?\\d+");

    public static Collection<Object[]> parseCaseForTest(Class<?> testClass) throws IOException {
        final String relativeUri = testClass.getSimpleName() + ".txt";
        final URL url = Resources.getResource(testClass, relativeUri);
        final String content = Resources.toString(url, Charsets.UTF_8);
        List<Object[]> cases = new ArrayList<Object[]>();
        for (String line : Splitter.on("\n").trimResults().omitEmptyStrings().split(content)) {
            final Walker w = new Walker(line).skip(DELIMITER);
            final List<Object> params = new ArrayList<Object>();
            for (final Class<?> ptype : paramTypes(testClass)) {
                if (ptype.isArray() && ptype.getComponentType().isAssignableFrom(int.class))
                    params.add(toIntArray(w));
                else if (ptype.isAssignableFrom(int.class)) {
                    params.add(Integer.parseInt(w.next(INT)));
                    w.skip(DELIMITER);
                } else if (ptype.isAssignableFrom(String.class)) {
                    w.skip(QUOTE);
                    params.add(w.next(NOQUOTE));
                    w.skip(QUOTE).skip(DELIMITER);
                } else throw new AssertionError("Unsupported type " + ptype);
            }
            cases.add(params.toArray());
        }
        return cases;
    }

    private static int[] toIntArray(Walker w) {
        final List<Integer> values = new ArrayList<Integer>();
        w.skip(OPEN).skip(DELIMITER);
        while (!w.hasNext(CLOSE)) {
            values.add(Integer.parseInt(w.next(INT)));
            w.skip(DELIMITER);
        }
        w.consume().skip(DELIMITER);
        return Ints.toArray(values);
    }

    private static List<Class<?>> paramTypes(Class<?> testClass) throws SecurityException {
        final List<Class<?>> result = new ArrayList<>();
        for (final Field f : testClass.getFields()) {
            Parameter param = f.getAnnotation(Parameter.class);
            if (param == null) continue;
            while (result.size() < param.value()) result.add(null);
            result.set(param.value(), f.getType());
        }
        if (result.isEmpty())
            return Arrays.asList(testClass.getConstructors()[0].getParameterTypes());
        return result;
    }
}
