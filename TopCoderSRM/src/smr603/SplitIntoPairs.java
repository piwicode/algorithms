/*
 * Solution proposal to TopCoder Single Round Match
 */
package smr603;

/**
 * Problem Statement
 *     
 * You are given a int[] A with N elements, where N is even. Note that some elements of A may be negative. You are also
 * given a int X which is guaranteed to be negative. You must divide the elements of A into N/2 disjoint pairs. (That
 * is, each element of A must be in exactly one of those pairs.) Your goal is to maximize the number of pairs in which
 * the product of the two elements is greater than or equal to X. Return the largest possible number of such pairs.
 * Definition
 *     
 * Class:
 * SplitIntoPairs
 * Method:
 * makepairs
 * Parameters:
 * int[], int
 * Returns:
 * int
 * Method signature:
 * int makepairs(int[] A, int X)
 * (be sure your method is public)
 * Limits
 *     
 * Time limit (s):
 * 2.000
 * Memory limit (MB):
 * 256
 * Constraints
 * -
 * A will contain between 2 and 50 elements, inclusive.
 * -
 * The number of elements in A will be even.
 * -
 * Each element in A will be between -1,000,000,000 and 1,000,000,000, inclusive.
 * -
 * X will be between -1,000,000,000 and -1, inclusive.
 * Examples
 * 0)
 *
 *     
 * {2,-1}
 * -1
 * Returns: 0
 * One possible pair has product -2, which is lower than needed.
 * 1)
 *
 *     
 * {1,-1}
 * -1
 * Returns: 1
 * Here product is -1, it's enough.
 * 2)
 *
 *     
 * {-5,-4,2,3}
 * -1
 * Returns: 2
 * If first pair contains numbers -5 and -4, and second contains 2 and 3, both will have positive product.
 * 3)
 *
 *     
 * {5,-7,8,-2,-5,3}
 * -7
 * Returns: 3
 * Acceptable splitting is {5,8}, {-7,-5} and {-2,3}.
 * 4)
 *
 *     
 * {3,4,5,6,6,-6,-4,-10,-1,-9}
 * -2
 * Returns: 4
 *
 * 5)
 *
 *     
 * {1000000,1000000}
 * -5
 * Returns: 1
 * Beware overflow.
 * This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or
 * reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003,
 * TopCoder, Inc. All rights reserved.
 *
 */
public class SplitIntoPairs {
    public static int makepairs(int[] n, int x) {
        long minpos = Integer.MAX_VALUE, pos = 0;
        long maxneg = Integer.MIN_VALUE, neg = 0;
        for (int i : n) {
            if (i < 0) {
                neg++;
                maxneg = Math.max(maxneg, i);
            } else {
                pos++;
                minpos = Math.min(minpos, i);
            }
        }
        //Ther is an odd number of negative integer and the best combination fails
        if ((neg & 1) == 1 && maxneg * minpos < x)
            return n.length / 2 - 1;
        return n.length / 2;
    }
}
