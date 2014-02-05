
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseballElimination {
    private final int nbTeam;
    private final List<String> teams = new ArrayList<String>();
    private final int[] wins, loss, left;
    private final int[][] g;
    private final List<String>[] certificates;

    /**
     * create a baseball division from given filename in format specified below
     */
    public BaseballElimination(String filename) {
        final In in = new In(filename);
        nbTeam = in.readInt();
        if (nbTeam <= 0) {
            throw new IllegalArgumentException();
        }
        certificates = new List[nbTeam];
        wins = new int[nbTeam];
        loss = new int[nbTeam];
        left = new int[nbTeam];
        g = new int[nbTeam][nbTeam];
        for (int t = 0; t < nbTeam; t++) {
            teams.add(in.readString());
            wins[t] = in.readInt();
            loss[t] = in.readInt();
            left[t] = in.readInt();
            for (int i = 0; i < nbTeam; i++) {
                g[t][i] = in.readInt();
            }
        }
    }

    /**
     * number of teams
     */
    public int numberOfTeams() {
        return nbTeam;
    }

    /**
     * all teams
     */
    public Iterable<String> teams() {
        return Collections.unmodifiableList(teams);
    }

    /**
     * number of wins for given team
     */
    public int wins(String team) {
        return wins[ teamIndex(team)];
    }

    private int teamIndex(String team) throws IllegalArgumentException {
        final int indexOf = teams.indexOf(team);
        if (indexOf == -1) {
            throw new IllegalArgumentException();
        }
        return indexOf;
    }

    /**
     * number of losses for given team
     */
    public int losses(String team) {
        return loss[ teamIndex(team)];
    }

    /**
     * number of remaining games for given team
     */
    public int remaining(String team) {
        return left[ teamIndex(team)];
    }

    /**
     * number of remaining games between team1 and team2
     */
    public int against(String team1, String team2) {
        return g[teamIndex(team1)][teamIndex(team2)];
    }

    /**
     * is given team eliminated?
     */
    public boolean isEliminated(String team) {
        return !certificateFor(team).isEmpty();
    }

    /**
     * subset R of teams that eliminates given team; null if not eliminated
     */
    public Iterable<String> certificateOfElimination(String team) {
        final List<String> c = certificateFor(team);
        return c.isEmpty() ? null : c;
    }

    private List<String> certificateFor(String team) throws IllegalArgumentException {
        int tidx = teamIndex(team);
        if (certificates[tidx] == null) {
            certificates[tidx] = computeCertificate(tidx);
        }
        return certificates[tidx];
    }

    private List<String> computeCertificate(int tidx) {
        final int firstMatch = 2;
        final int firstTeamVertex = firstMatch + nbTeam * nbTeam;
        final FlowNetwork fn = new FlowNetwork(firstTeamVertex + nbTeam);
        final ArrayList<String> cert = new ArrayList<String>();
        int current = firstMatch;
        for (int i = 0; i < nbTeam; i++) {
            if (i == tidx) {
                continue;
            }
            int canWin = wins[tidx] + left[tidx] - wins[i];
            if (canWin < 0) {
                cert.add(teams.get(i));
                continue;
            }
            fn.addEdge(new FlowEdge(firstTeamVertex + i, 1, canWin));
            for (int j = i + 1; j < nbTeam; j++) {
                if (g[i][j] == 0 || j == tidx) {
                    continue;
                }
                fn.addEdge(new FlowEdge(0, current, g[i][j]));
                fn.addEdge(new FlowEdge(current, firstTeamVertex + i, Double.POSITIVE_INFINITY));
                fn.addEdge(new FlowEdge(current, firstTeamVertex + j, Double.POSITIVE_INFINITY));
                current++;
            }
        }
        if (!cert.isEmpty()) {
            return cert;
        }
        FordFulkerson ff = new FordFulkerson(fn, 0, 1);

        for (int i = 0; i < nbTeam; i++) {
            if (ff.inCut(firstTeamVertex + i)) {
                cert.add(teams.get(i));
            }
        }
        return cert;
    }

}
