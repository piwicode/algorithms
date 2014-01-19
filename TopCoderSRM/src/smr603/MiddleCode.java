/*
 * Solution proposal to TopCoder Single Round Match
 */
package smr603;

/**
 * Problem Statement
 *     
 * Hero is learning a new algorithm to encode a string. You are given a String s that consists of lowercase letters
 * only. Your task is to simulate the algorithm described below on this string, so that Hero can see how it works. The
 * algorithm starts with a given input string s and an empty output string t. The execution of the algorithm consists of
 * multiple steps. In each step, s and t are modified as follows:
 * If the length of s is odd, the middle character of s is added to the end of t, and then deleted from s.
 * If the length of s is even, the two characters in the middle of s are compared. The smaller one of them (either one
 * in case of a tie) is added to the end of t, and then deleted from s.
 * If after some step the string s is empty, the algorithm terminates. The output of the algorithm is the final string
 * t. Return the String t that will be produced by the above algorithm for the given String s.
 * Definition
 *     
 * Class:
 * MiddleCode
 * Method:
 * encode
 * Parameters:
 * String
 * Returns:
 * String
 * Method signature:
 * String encode(String s)
 * (be sure your method is public)
 * Limits
 *     
 * Time limit (s):
 * 2.000
 * Memory limit (MB):
 * 256
 * Notes
 * -
 * When comparing letters, the smaller one is the one earlier in the alphabet - i.e., the character with the smaller
 * ASCII code.
 * Constraints
 * -
 * s will contain between 1 and 50 characters, inclusive.
 * -
 * Each character in s will be a lowercase letter ('a'-'z').
 * Examples
 * 0)
 *
 *     
 * "word"
 * Returns: "ordw"
 * In the first step, 'o' is smaller than 'r', thus 'o' is selected.
 * After the first step: s="wrd", t="o".
 * After the second step: s="wd", t="or".
 * In the third step, 'w' is greater than 'd', thus 'd' is selected.
 * After the third step: s="w", t="ord".
 * After the fourth step: s="", t="ordw", and the algorithm terminates.
 * 1)
 *
 *     
 * "aaaaa"
 * Returns: "aaaaa"
 *
 * 2)
 *
 *     
 * "abacaba"
 * Returns: "caabbaa"
 *
 * 3)
 *
 *     
 * "shjegr"
 * Returns: "ejghrs"
 *
 * 4)
 *
 *     
 * "adafaaaadafawafwfasdaa"
 * Returns: "afawadafawafaafsadadaa"
 *
 * This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or
 * reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003,
 * TopCoder, Inc. All rights reserved.
 *
 * @author Pierre
 */
public class MiddleCode {
    public static String encode(String s) {
        StringBuilder in = new StringBuilder(s);
        StringBuilder out = new StringBuilder();
        while (in.length() != 0) {
            int p = in.length() / 2;
            if ((in.length() & 1) == 0 && in.charAt(p - 1) < in.charAt(p))
                p = p - 1;
            char c = in.charAt(p);
            in.deleteCharAt(p);
            out.append(c);
        }
        return out.toString();
    }
}
