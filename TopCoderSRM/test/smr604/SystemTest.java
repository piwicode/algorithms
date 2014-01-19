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
    private static final Pattern DOUBLE = doublePattern();

    public static Collection<Object[]> parseCaseForTest(Class<?> testClass) throws IOException {
        final String relativeUri = testClass.getSimpleName() + ".txt";
        final URL url = Resources.getResource(testClass, relativeUri);
        final String content = Resources.toString(url, Charsets.UTF_8);
        List<Object[]> cases = new ArrayList<Object[]>();
        for (String line : Splitter.on("\n").trimResults().omitEmptyStrings().split(content)) {
            final Walker w = new Walker(line).skip(DELIMITER);
            final List<Object> params = new ArrayList<Object>();
            for (final Class<?> ptype : paramTypes(testClass)) {
                params.add(parseValue(ptype, w));
            }
            cases.add(params.toArray());
        }
        return cases;
    }

    private static Object parseValue(final Class<?> ptype, final Walker w) throws AssertionError, NumberFormatException {
        final Object value;
        if (ptype.isArray() && ptype.getComponentType().isAssignableFrom(int.class))
            value = Ints.toArray((List) toArray(w, ptype.getComponentType()));
        else if (ptype.isArray() && ptype.getComponentType().isAssignableFrom(String.class))
            value = toArray(w, ptype.getComponentType()).toArray(new String[0]);
        else if (ptype.isAssignableFrom(int.class))
            value = Integer.parseInt(w.next(INT));
        else if (ptype.isAssignableFrom(double.class))
            value = Double.parseDouble(w.next(DOUBLE));
        else if (ptype.isAssignableFrom(String.class)) {
            w.skip(QUOTE);
            value = w.next(NOQUOTE);
            w.skip(QUOTE);
        } else throw new AssertionError("Type not supported: " + ptype);
        w.skip(DELIMITER);
        return value;
    }

    private static List<Object> toArray(Walker w, final Class<?> elemType) {
        final List<Object> values = new ArrayList<Object>();
        w.skip(OPEN).skip(DELIMITER);
        while (!w.hasNext(CLOSE))
            values.add(parseValue(elemType, w));
        w.consume().skip(DELIMITER);
        return values;
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

    private static final Pattern doublePattern() {
        final String Digits = "(\\p{Digit}+)";
        final String HexDigits = "(\\p{XDigit}+)";
        // an exponent is 'e' or 'E' followed by an optionally
        // signed decimal integer.
        final String Exp = "[eE][+-]?" + Digits;
        final String fpRegex
                = ("[\\x00-\\x20]*" + // Optional leading "whitespace"
                "[+-]?(" + // Optional sign character
                "NaN|" + // "NaN" string
                "Infinity|"
                + // "Infinity" string
                // A decimal floating-point string representing a finite positive
                // number without a leading sign has at most five basic pieces:
                // Digits . Digits ExponentPart FloatTypeSuffix
                //
                // Since this method allows integer-only strings as input
                // in addition to strings of floating-point literals, the
                // two sub-patterns below are simplifications of the grammar
                // productions from section 3.10.2 of
                // The Java™ Language Specification.
                // Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
                "(((" + Digits + "(\\.)?(" + Digits + "?)(" + Exp + ")?)|"
                + // . Digits ExponentPart_opt FloatTypeSuffix_opt
                "(\\.(" + Digits + ")(" + Exp + ")?)|"
                + // Hexadecimal strings
                "(("
                + // 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
                "(0[xX]" + HexDigits + "(\\.)?)|"
                + // 0[xX] HexDigits_opt . HexDigits BinaryExponent FloatTypeSuffix_opt
                "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")"
                + ")[pP][+-]?" + Digits + "))"
                + "[fFdD]?))"
                + "[\\x00-\\x20]*");// Optional trailing "whitespace"
        return Pattern.compile(fpRegex);
    }
}
