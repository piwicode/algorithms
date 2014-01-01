/*
 * Solution proposal to coursera Algorithms Part 2
 * Programming Assignment 5: Burrow-Wheeler
 */
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Pierre
 */
public class BWCoderDecoderTest {

    public static String randomString(int n) {
        final StringBuilder sb = new StringBuilder();
        final Random r = new Random();
        for (int i = 0; i < n; i++) {
            sb.append(r.nextInt() & 0xff);
        }
        return sb.toString();
    }

    @Test
    public void testCodeAbracadabra() {
        CharSinkBuf coded = new CharSinkBuf();
        BWCoderDecoder.code("ABRACADABRA!", coded);
        Assert.assertEquals("\u0000\u0000\u0000\u0003ARD!RCAAAABB", coded.buf.toString());
    }

    @Test
    public void testDecodeAbracadabra() {
        CharSinkBuf decoded = new CharSinkBuf();
        BWCoderDecoder.decode(3, "ARD!RCAAAABB", decoded);
        Assert.assertEquals("ABRACADABRA!", decoded.buf.toString());
    }

    @Test
    public void testSomeMethod() {
        String rs = randomString(1024);
        roundTrip(rs);
    }

    @Test
    public void star() {
        roundTrip("*************");
    }

    @Test
    public void couscous() {
        roundTrip("couscous");
    }

    private void roundTrip(String rs) {
        CharSinkBuf coded = new CharSinkBuf();
        BWCoderDecoder.code(rs, coded);
        int first = (coded.buf.charAt(0) << 24) + (coded.buf.charAt(1) << 16) + (coded.buf.charAt(2) << 8) + coded.buf.charAt(3);
        coded.buf.delete(0, 4);
        CharSinkBuf actual = new CharSinkBuf();
        BWCoderDecoder.decode(first, coded.buf, actual);
        Assert.assertEquals(rs, actual.buf.toString());
    }
}
