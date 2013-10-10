import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 4: 8 Puzzle
 */
/**
 * find a solution to the initial board (using the A* algorithm)
 *
 */
public class Solver {

    private final List<Board> solution;

    public Solver(Board initial) {
        final SolvingProcess input = new SolvingProcess(initial);
        final SolvingProcess twin = new SolvingProcess(initial.twin());
        List<Board> sol = null;
        do {
            sol = input.doOneStep();
            if (sol != null) {
                break;
            }
            if (twin.doOneStep() != null) {
                break;
            }
        } while (true);
        solution = sol;
    }

    /**
     * is the initial board solvable?
     *
     */
    public boolean isSolvable() {
        return solution != null;
    }

    /**
     * min number of moves to solve initial board; -1 if no solution
     *
     */
    public int moves() {
        return solution != null ? solution.size() - 1 : -1;
    }

    /**
     * sequence of boards in a shortest solution; null if no solution
     *
     */
    public Iterable<Board> solution() {
        return solution;
    }

    private static class Position implements Comparable<Position> {

        private final Position previous;
        private final Board board;
        private final int moves;

        public Position(Board board, Position previous) {
            this.previous = previous;
            this.board = board;
            this.moves = previous != null ? previous.moves + 1 : 0;
        }

        private int priority() {
            return moves + board.manhattan();
        }

        @Override
        public int compareTo(Position o) {
            return priority() - o.priority();
        }

        public boolean comesFrom(Board b) {
            return board.equals(b) || (previous != null && previous.comesFrom(b));
        }

        private List<Board> toList() {
            final ArrayList<Board> res = new ArrayList<Board>();
            for (Position p = this; p != null; p = p.previous) {
                res.add(p.board);
            }
            Collections.reverse(res);
            return res;
        }
    }

    private class SolvingProcess {

        private final MinPQ<Position> candidates = new MinPQ<Position>();

        public SolvingProcess(final Board initial) {
            candidates.insert(new Position(initial, null));
        }

        public List<Board> doOneStep() {
            final Position pos = candidates.delMin();
            if (pos.board.isGoal()) {
                return pos.toList();
            }
            for (Board neighbor : pos.board.neighbors()) {
                if (!pos.comesFrom(neighbor)) {
                    candidates.insert(new Position(neighbor, pos));
                }
            }
            return null;
        }

    }

    /**
     * solve a slider puzzle (given below)
     *
     */
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
    }
}
