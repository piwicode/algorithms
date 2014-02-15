/*
 * Solution proposal to TopCoder Single Round Match
 */

package smr609;


public class MagicalStringDiv2 {
    public static int minimalMoves(String S){
        int count = 0;
        int i=0,j=S.length()-1;
        while(i<j){
            count+=S.charAt(i++)=='<'?1:0;
            count+=S.charAt(j--)=='>'?1:0;            
        }
        return count;
    }
}
