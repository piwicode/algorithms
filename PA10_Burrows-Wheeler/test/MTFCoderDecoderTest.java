/*
 * Solution proposal to coursera Algorithms Part 2
 * Programming Assignment 5: Burrow-Wheeler
 */
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class MTFCoderDecoderTest {

    public MTFCoderDecoderTest() {
    }

    @Test
    public void encode() {
        MTFCoderDecoder mtf = new MTFCoderDecoder();
        assertEquals((byte) 0, mtf.encode((byte) 0));
        assertEquals((byte) 0, mtf.encode((byte) 0));
        assertEquals((byte) 0, mtf.encode((byte) 0));
        assertEquals((byte) 1, mtf.encode((byte) 1));
        assertEquals((byte) 0, mtf.encode((byte) 1));
        assertEquals((byte) 1, mtf.encode((byte) 0));
        assertEquals((byte) 0, mtf.encode((byte) 0));
        assertEquals((byte) 255, mtf.encode((byte) 255));
        assertEquals((byte) 0, mtf.encode((byte) 255));
        assertEquals((byte) 1, mtf.encode((byte) 0));
        assertEquals((byte) 2, mtf.encode((byte) 1));
    }

    @Test
    public void roundtrip() {
        byte[] buf = new byte[1024 * 1024];
        Random rnd = new Random();
        rnd.nextBytes(buf);

        MTFCoderDecoder enc = new MTFCoderDecoder();
        MTFCoderDecoder dec = new MTFCoderDecoder();

        for (byte b : buf) {
            byte coded = enc.encode(b);
            byte decoded = dec.decode(coded);
            assertEquals(b, decoded);
        }
    }

}
