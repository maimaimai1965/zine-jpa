package ua.mai.zine.leetcode;

/***
 * 5. Longest Palindromic Substring
 */
class Solution0005 {

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 0)
            return s;

        String maxS = "";

        for (int leftI = 0; leftI < s.length(); leftI++ ) {
            lab_f2:
            for (int rightJ = s.length()-1; rightJ >= leftI; rightJ--) {
                int i = leftI;
                int j = rightJ;
                int lastJ = 0;

                while (j >= i) {
                   if (s.charAt(i) == s.charAt(j)) {
                       if (i == j-1 || i == j) {
                           if (lastJ == 0)
                               lastJ = j;
                           if (lastJ - leftI + 1 > maxS.length()) {
                               maxS = s.substring(leftI, lastJ + 1);
                               break lab_f2;
                           } else {
                               if (lastJ == 0)
                                   lastJ = j;
                               i++;
                               j--;
                           }
                       } else {
                           if (lastJ == 0)
                               lastJ = j;
                           i++;
                           j--;
                       }
                   } else {
                       break;
                   }
                }
            }
        }
        System.out.println(s + " -> " + maxS);
        return maxS;
    }

    public static void main(String[] args) {
        Solution0005 s = new Solution0005();
        s.longestPalindrome("babRv1vRad");
        s.longestPalindrome("babkkkkkkad");
        s.longestPalindrome("b");
        s.longestPalindrome("bd");
    }

}