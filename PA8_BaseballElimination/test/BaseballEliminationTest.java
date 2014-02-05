/*
 * Solution proposal to coursera Algorithms Part 2
 * Programming Assignment 3: Baseball Elimination
 */

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pierre
 */
public class BaseballEliminationTest {

    public BaseballEliminationTest() {
    }
    String file = BaseballEliminationTest.class.getResource("teams_4.txt").getFile();
    final BaseballElimination be = new BaseballElimination(file);

    @Test
    public void testNumberOfTeams() {
        assertEquals(0, be.against("Atlanta", "Atlanta"));
        assertEquals(1, be.against("Atlanta", "Philadelphia"));
        assertEquals(6, be.against("Atlanta", "New_York"));
        assertEquals(0, be.against("Montreal", "New_York"));
        assertEquals(79, be.losses("Philadelphia"));
        assertEquals(78, be.losses("New_York"));
        assertEquals(6, be.remaining("New_York"));
        assertEquals(8, be.remaining("Atlanta"));
        assertEquals(83, be.wins("Atlanta"));
        assertEquals(77, be.wins("Montreal"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void test_losses_throws_iae() {
        be.losses("ouioui");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_wins_throws_iae() {
        be.wins("ouioui");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_remaining_throws_iae() {
        be.remaining("ouioui");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_isEliminated_throws_iae() {
        be.isEliminated("ouioui");
    }

    @Test
    public void testLosses() {
        assertTrue(be.isEliminated("Montreal"));
        final List<String> c = (List<String>) be.certificateOfElimination("Montreal");
        assertArrayEquals(new String[]{"Atlanta"}, (String[]) c.toArray(new String[0]));

        assertNull(be.certificateOfElimination("Atlanta"));
        assertFalse(be.isEliminated("Atlanta"));

        final List<String> d = (List<String>) be.certificateOfElimination("Philadelphia");
        assertArrayEquals(new String[]{"Atlanta", "New_York"}, (String[]) d.toArray(new String[0]));
        assertTrue(be.isEliminated("Philadelphia"));

        assertNull(be.certificateOfElimination("New_York"));
        assertFalse(be.isEliminated("New_York"));
    }

    @Test
    public void testRemaining() {
        String file = BaseballEliminationTest.class.getResource("teams_12.txt").getFile();
        final BaseballElimination be = new BaseballElimination(file);
        System.out.println(be.certificateOfElimination("Japan"));
    }

    @Test
    public void testAgainst() {
    }

    @Test
    public void testIsEliminated() {
    }

    @Test
    public void testCertificateOfElimination() {
    }

}
