/*
 * Solution proposal to TopCoder Single Round Match
 */

package smr609;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class MagicalStringDiv2Test {
    
    public MagicalStringDiv2Test() {
    }

    @Test
    public void testMinimalMoves() {
        assertEquals(0, MagicalStringDiv2.minimalMoves("><"));
        assertEquals(1, MagicalStringDiv2.minimalMoves("<<"));
        assertEquals(20, MagicalStringDiv2.minimalMoves("<><<<>>>>><<>>>>><>><<<>><><><><<><<<<<><<>>><><><"));
        assertEquals(0, MagicalStringDiv2.minimalMoves(">><<"));
        assertEquals(1, MagicalStringDiv2.minimalMoves(">>><"));
        assertEquals(2, MagicalStringDiv2.minimalMoves(">><<><"));
        for(int i = 0 ; i < 50 ; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0 ; j < i ; j ++)            
                sb.append(">");
            for(int j = 0 ; j < i ; j ++)            
                sb.append("<");
            assertEquals(0, MagicalStringDiv2.minimalMoves(sb.toString()));
        }
        assertEquals(2, MagicalStringDiv2.minimalMoves(">><<><"));
    }
    
}
