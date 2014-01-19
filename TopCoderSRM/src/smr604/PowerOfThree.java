package smr604;

/**
 * Fox Ciel has a robot. The robot is located on an infinite plane. At the
 * beginning, the robot starts at the coordinates (0, 0). The robot can then
 * make several steps. The steps are numbered starting from 0.
 *
 * In each step, Ciel must choose one of four directions for the robot: left (x
 * coordinate decreases), right (x coordinate increases), up (y coordinate
 * increases), or down (y coordinate decreases). In step k, the robot will move
 * 3^k units in the chosen direction. It is not allowed to skip a step.
 *
 * You are given ints x and y. Return "Possible" (quotes for clarity) if it is
 * possible that the robot reaches the point (x,y) after some (possibly zero)
 * steps. Otherwise, return "Impossible".
 */
public class PowerOfThree {

    /**
     * x can be changed to -x without affecting the result. The same is true for
     * y.
     *
     * Lets consider k=0 for tx positive
     * <pre>
     * if tx % 3 == 0 then the k=0 move must be vertical -> check ty
     * if tx % 3 == 1 then the k=0 move is left
     * if tx % 3 == 2 then k=0 is right and k=1 is left
     * </pre>
     *
     * If a move is detected in both dimensions then it is Impossible. One or
     * the other dimension must be null but not both. This is the XOR operator
     * implemented by == operator : (tx3 == 0) == (ty3 == 0)
     *
     * Dividing 'tx' by three allows to analyze the move k=1. But in case of a
     * sequence of opposed move (a.k.a. |tx % 3| == 2) there is a need to
     * compensate the effect of current move by adding or sub-stracting one.
     * This is implemented by "tx3 / 2" ;
     *
     */
    public static String ableToGet(int tx, int ty) {
        while (tx != 0 || ty != 0) {
            int tx3 = tx % 3, ty3 = ty % 3;
            if ((tx3 == 0) == (ty3 == 0)) return "Impossible";
            tx = tx / 3 + tx3 / 2;
            ty = ty / 3 + ty3 / 2;
        }
        return "Possible";
    }

}
