package smr604;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class FoxConnectionTest {

    /**
     * {1,2,3} {2,3,4} "YNNY" Returns: 2 Treeland looks as follows: 1-2-3-4. Two
     * foxes are located in city 1 and city 4. One optimal solution is: The fox
     * located in city 1 moves to city 2. The fox located in city 4 moves to
     * city 3. 1)
     */
    @Test
    public void test_line_1_0_0_1() {
        assertEquals(2, FoxConnection.minimalDistance(
                new int[]{1, 2, 3},
                new int[]{2, 3, 4},
                "YNNY"));
    }
    /*
     {1,1,1,1}
     {2,3,4,5}
     "NYYYY"
     Returns: 1
     We can move any one of the foxes to city 1. After that the cities with foxes will form a connected set.
     */

    @Test
    public void test_star() {
        assertEquals(1, FoxConnection.minimalDistance(
                new int[]{1, 1, 1, 1},
                new int[]{2, 3, 4, 5},
                "NYYYY"));
    }
    /*  
     {1,3,4,5,4}
     {2,2,2,4,6}
     "YNYNYY"
     Returns: 2
     */

    @Test
    public void test_3() {
        assertEquals(2, FoxConnection.minimalDistance(
                new int[]{1, 3, 4, 5, 4},
                new int[]{2, 2, 2, 4, 6},
                "YNYNYY"));
    }

    /*  
     {1,2,3,4,5,6,7,8,9}
     {2,3,4,5,6,7,8,9,10}
     "YNNNYNYNNY"
     Returns: 7
     */
    @Test
    public void test_4() {
        assertEquals(7, FoxConnection.minimalDistance(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9},
                new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10},
                "YNNNYNYNNY"));
    }
    /* 
     {1,2,3,4,3,6,8,7}
     {2,3,4,5,6,8,9,6}
     "YNNYYNYYY"
     Returns: 3
       */

    @Test
    public void test_5() {
        assertEquals(3, FoxConnection.minimalDistance(
                new int[]{1, 2, 3, 4, 3, 6, 8, 7},
                new int[]{2, 3, 4, 5, 6, 8, 9, 6},
                "YNNYYNYYY"));
    }
    /*   
     {1}
     {2}
     "NY"
     Returns: 0
     There can be only 1 fox.
       */

    @Test
    public void test_6() {
        assertEquals(0, FoxConnection.minimalDistance(
                new int[]{1},
                new int[]{2},
                "NY"));
    }
    /*   
     {1}
     {2}
     "NN"
     Returns: 0
     And there can be no foxes.
     */

    @Test
    public void test_7() {
        assertEquals(0, FoxConnection.minimalDistance(
                new int[]{1},
                new int[]{2},
                "NN"));
    }

}
