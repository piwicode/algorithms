/*
 * Solution proposal to TopCoder Single Round Match
 */
package smr603;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class PairsOfStringsTest {

    public PairsOfStringsTest() {
    }

    @Test
    public void testGetNumber() {
        System.out.println((Float.intBitsToFloat(Float.floatToIntBits(Short.MAX_VALUE+1) - 1)));
        System.out.println((short) (Float.intBitsToFloat(Float.floatToIntBits(Short.MAX_VALUE+1) - 1)));
        System.out.println((Float.intBitsToFloat(Float.floatToIntBits(Short.MAX_VALUE+1))));
        System.out.println((short) (Float.intBitsToFloat(Float.floatToIntBits(Short.MAX_VALUE+1))));

        System.out.println("*"+(short) 0x8000);
        System.out.println((Float.intBitsToFloat(Float.floatToIntBits(Short.MIN_VALUE-1) - 1)));
        System.out.println((short) (Float.intBitsToFloat(Float.floatToIntBits(Short.MIN_VALUE-1) - 1)));
        System.out.println((Float.intBitsToFloat(Float.floatToIntBits(Short.MIN_VALUE-1) )));
        System.out.println((short) (Float.intBitsToFloat(Float.floatToIntBits(Short.MIN_VALUE-1) )));
        short shortValue = (short) Float.POSITIVE_INFINITY;
        System.out.println(shortValue);
    }

}
