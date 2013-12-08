/*
 * Solution proposal to coursea Algorithms Part 2
 * Programming Assignment 1: WordNet
 */
import java.util.Collections;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class SAPTest {

    public SAPTest() {
    }

    @Test(expected = NullPointerException.class)
    public void ctor_null() {
        new SAP(null);
    }

    @Test()
    public void empty_graph() {
        new SAP(new Digraph(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void empty_graph_ancestor() {
        new SAP(new Digraph(0)).ancestor(0, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void empty_graph_length() {
        new SAP(new Digraph(0)).length(0, 0);
    }

    @Test
    public void singeton_graph_length() {
        assertEquals(0, new SAP(new Digraph(1)).length(0, 0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_length_too_big_1() {
        assertEquals(0, new SAP(new Digraph(1)).length(1, 0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_length_too_big_2() {
        assertEquals(0, new SAP(new Digraph(1)).length(0, 1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_length_too_small_1() {
        assertEquals(0, new SAP(new Digraph(1)).length(-1, 0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_length_too_small_2() {
        assertEquals(0, new SAP(new Digraph(1)).length(0, -1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_ancestor_too_big_1() {
        assertEquals(0, new SAP(new Digraph(1)).ancestor(1, 0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_ancestor_too_big_2() {
        assertEquals(0, new SAP(new Digraph(1)).ancestor(0, 1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_ancestor_too_small_1() {
        assertEquals(0, new SAP(new Digraph(1)).ancestor(-1, 0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_ancestor_too_small_2() {
        assertEquals(0, new SAP(new Digraph(1)).ancestor(0, -1));
    }

    @Test
    public void singeton_graph_ancestor() {
        assertEquals(0, new SAP(new Digraph(1)).ancestor(0, 0));
    }

    @Test
    public void simple_graph() {
        final Digraph dg = new Digraph(3);
        dg.addEdge(1, 0);
        dg.addEdge(2, 0);
        final SAP sap = new SAP(dg);
        testFlip(0, 0, sap, 0, 0);
        testFlip(0, 1, sap, 1, 1);
        testFlip(0, 2, sap, 2, 2);

        testFlip(2, 0, sap, 1, 2);
        testFlip(1, 0, sap, 0, 1);
        testFlip(1, 0, sap, 0, 2);
    }

    private void testFlip(int expectedLength, int expectedAncestor,
            SAP sap, int v1, int v2) {
        assertEquals(expectedLength, sap.length(v1, v2));
        assertEquals(expectedLength, sap.length(v2, v1));
        assertEquals(expectedAncestor, sap.ancestor(v1, v2));
        assertEquals(expectedAncestor, sap.ancestor(v2, v1));
        assertEquals(expectedLength, sap.length(Collections.singletonList(v1), Collections.singletonList(v2)));
        assertEquals(expectedLength, sap.length(Collections.singletonList(v2), Collections.singletonList(v1)));
        assertEquals(expectedAncestor, sap.ancestor(Collections.singletonList(v1), Collections.singletonList(v2)));
        assertEquals(expectedAncestor, sap.ancestor(Collections.singletonList(v2), Collections.singletonList(v1)));
    }

    
}
