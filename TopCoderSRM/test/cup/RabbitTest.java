/*
 * Solution proposal to TopCoder Single Round Match
 */

package cup;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class RabbitTest {
    
    public RabbitTest() {
    }

    @Test
    public void testSomeMethod() {
        for(int N = 1 ; N < 24;N++){
            String ref=Rabbit.bfConcat(N);
            System.out.println(ref);
            for(int i = 1 ; i < ref.length();i++){
                assertEquals("n:"+N+" i:"+i,ref.charAt(i), Rabbit.get(N, i));
            }
        }
    }
    
}
