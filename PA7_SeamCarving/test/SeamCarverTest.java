/*
 * Solution proposal to coursera Algorithms Part 2
 * Programming Assignment 2: Seam Carving
 */
import java.awt.Color;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class SeamCarverTest {

    public SeamCarverTest() {
    }

    @Test
    public void testSomeMethod() {
        final Picture pic = toPicture(new int[][]{
            {0x000000, 0x000500, 0x000000},
            {0x000001, 0x000001, 0x000005},
            {0x000000, 0x000700, 0x010000}});
        SeamCarver seamCarver = new SeamCarver(pic);
        assertTrue(20. == seamCarver.energy(1, 1));

        seamCarver.removeHorizontalSeam(new int[]{0, 1, 2});
        assertPictureEquals(seamCarver.picture(), new int[][]{
            {0x000001, 0x000500, 0x000000},
            {0x000000, 0x000700, 0x000005}
        });
    }

    @Test
    public void testSomeMethod2() {
        final Picture pic = toPicture(new int[][]{
            {0x010203, 0x040506, 0x070809},
            {0x101112, 0x131415, 0x161718},
            {0x192021, 0x222324, 0x252627}});
        SeamCarver seamCarver = new SeamCarver(pic);
        assertTrue(2808. == seamCarver.energy(1, 1));

        seamCarver.removeVerticalSeam(new int[]{0, 1, 2});
        assertPictureEquals(seamCarver.picture(), new int[][]{
            {0x040506, 0x070809},
            {0x101112, 0x161718},
            {0x192021, 0x222324}
        });
    }

    @Test
    public void test_10_12() {
        SeamCarver seamCarver = new SeamCarver(new Picture(getClass().getResource("10x12.png").getFile().toString()));
        assertArrayEquals(new int[]{8,9,10,10,10,9,10,10,9,8}, seamCarver.findHorizontalSeam());
        assertArrayEquals(new int[]{5,6,7,8,7,7,6,7,6,5,6,5}, seamCarver.findVerticalSeam());
    }
    
    @Test
    public void test_12_10() {
        SeamCarver seamCarver = new SeamCarver(new Picture(getClass().getResource("12x10.png").getFile().toString()));
        assertArrayEquals(new int[]{7,8,7,8,7,6,5,6,6,5,4,3}, seamCarver.findHorizontalSeam());
        assertArrayEquals(new int[]{6,7,7,6,6,7,7,7,8,7}, seamCarver.findVerticalSeam());
    }
    @Test
    public void test_3_7() {
        SeamCarver seamCarver = new SeamCarver(new Picture(getClass().getResource("3x7.png").getFile().toString()));
        assertArrayEquals(new int[]{1, 2, 1}, seamCarver.findHorizontalSeam());
        assertArrayEquals(new int[]{0, 1, 1, 1, 1, 1, 0}, seamCarver.findVerticalSeam());
    }

    @Test
    public void test_4_6() {
        SeamCarver seamCarver = new SeamCarver(new Picture(getClass().getResource("4x6.png").getFile().toString()));
        assertArrayEquals(new int[]{1, 2, 1, 0}, seamCarver.findHorizontalSeam());
        assertArrayEquals(new int[]{1, 2, 1, 1, 2, 1}, seamCarver.findVerticalSeam());
    }

    @Test
    public void test_5_6() {
        SeamCarver seamCarver = new SeamCarver(new Picture(getClass().getResource("5x6.png").getFile().toString()));
        assertArrayEquals(new int[]{1, 2, 2, 3, 2, 1}, seamCarver.findVerticalSeam());
        assertArrayEquals(new int[]{2, 3, 2, 3, 2}, seamCarver.findHorizontalSeam());
    }

    @Test
    public void test_6_5() {
        SeamCarver seamCarver = new SeamCarver(new Picture(getClass().getResource("6x5.png").getFile().toString()));
        assertArrayEquals(new int[]{2, 3, 3, 3, 2}, seamCarver.findVerticalSeam());
        assertArrayEquals(new int[]{2, 3, 3, 3, 2, 1}, seamCarver.findHorizontalSeam());
    }

    @Test
    public void test_7_3() {
        SeamCarver seamCarver = new SeamCarver(new Picture(getClass().getResource("7x3.png").getFile().toString()));
        assertArrayEquals(new int[]{0, 1, 1, 1, 1, 1, 0}, seamCarver.findHorizontalSeam());
        assertArrayEquals(new int[]{2, 3, 2}, seamCarver.findVerticalSeam());
    }

    private Picture toPicture(int[][] pix) {
        Picture pic = new Picture(pix[0].length, pix.length);
        for (int y = 0; y != pix.length; y++) {
            for (int x = 0; x != pix[y].length; x++) {
                pic.set(x, y, new Color(pix[y][x]));
            }
        }
        return pic;
    }

    private void assertPictureEquals(Picture picture, int[][] b) {
        assertEquals(picture.width(), b[0].length);
        assertEquals(picture.height(), b.length);
        for (int y = 0; y != b.length; y++) {
            for (int x = 0; x != b[y].length; x++) {
                assertEquals("(" + x + "," + y + ")", picture.get(x, y).getRGB() & 0xffffff, b[y][x]);
            }
        }

    }

}
