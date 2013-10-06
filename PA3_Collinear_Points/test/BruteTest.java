/*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 3: Collinear Points
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Pierre
 */
@RunWith(Parameterized.class)
public class BruteTest {

    private String readString(Path filePath) {
        try {
            return new String(Files.readAllBytes(filePath));
        } catch (IOException ex) {
            return "";
        }
    }

    private void writeString(final String outName, final Object algo) throws FileNotFoundException, IOException {
        final OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(outName));
        os.write(algo.toString());
        os.close();
        System.out.println("write " + outName);
    }

    interface Algo {

        void go(Point[] points);
    }

    static class TBrute extends Brute implements Algo {

        private StringBuilder sb = new StringBuilder();

        @Override
        void print(Object s) {
            sb.append(s);
        }

        @Override
        public String toString() {
            return sb.toString();
        }

    }

    static class TFast extends Fast implements Algo {

        private StringBuilder sb = new StringBuilder();

        @Override
        void print(Object s) {
            sb.append(s);
        }

        @Override
        public String toString() {
            return sb.toString();
        }

    }

    @Parameterized.Parameters(name = "{index} - {0}")
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"equidistant.txt"},
            {"grid4x4.txt"},
            {"grid5x5.txt"},
            {"grid6x6.txt"},
            {"horizontal100.txt"},
            {"horizontal25.txt"},
            {"horizontal5.txt"},
            {"horizontal50.txt"},
            {"horizontal75.txt"},
            {"inarow.txt"},
            {"input1.txt"},
            {"input10.txt"},
            {"input100.txt"},
            {"input10000.txt"},
            {"input150.txt"},
            {"input2.txt"},
            {"input20.txt"},
            {"input200.txt"},
            {"input250.txt"},
            {"input299.txt"},
            {"input3.txt"},
            {"input300.txt"},
            {"input350.txt"},
            {"input40.txt"},
            {"input48.txt"},
            {"input50.txt"},
            {"input56.txt"},
            {"input6.txt"},
            {"input8.txt"},
            {"input80.txt"},
            {"input9.txt"},
            {"kw1260.txt"},
            {"mystery10089.txt"},
            {"random152.txt"},
            {"random23.txt"},
            {"random38.txt"},
            {"random91.txt"},
            {"rs1423.txt"},
            {"vertical100.txt"},
            {"vertical25.txt"},
            {"vertical5.txt"},
            {"vertical50.txt"},
            {"vertical75.txt"},});
    }

    public BruteTest(final String caseName) throws IOException, URISyntaxException {
        inputPath = Paths.get(getClass().getResource(caseName).toURI());
        points = Util.parse(getClass().getResource(caseName).getFile());
        inputName = caseName;
    }
    final Point[] points;
    final Path inputPath;
    final String inputName;

    @Test
    public void testBrute() throws URISyntaxException, IOException {
        System.out.println(inputPath);
        if (points.length > 200) {
            return;
        }
        final String ref_value = readString(Paths.get(inputPath.toString().replace(".txt", ".ref")));
        final TBrute algo = new TBrute();
        algo.go(points);
        try {
            Assert.assertEquals(ref_value.trim(), algo.toString().trim());
        } catch (AssertionError e) {
            writeString(inputName.replace(".txt", ".ref"), algo);
            throw e;
        }
    }

    @Test
    public void testFast() throws URISyntaxException, IOException {
        System.out.println(inputPath);
        if (points.length > 200) {
            return;
        }
        final String ref_value = readString(Paths.get(inputPath.toString().replace(".txt", ".ref.fast")));        
        final TFast algo = new TFast();
        algo.go(points);
        try {
            Assert.assertEquals(ref_value.trim(), algo.toString().trim());
        } catch (AssertionError e) {
            writeString(inputName.replace(".txt", ".ref.fast"), algo);
            throw e;
        }
    }

}
