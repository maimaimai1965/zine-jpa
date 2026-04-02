package ua.mai.zine.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution0005Test {

    Solution0005 solution = new Solution0005();

    @Test
    void test1() {
        Solution0005 solution = new Solution0005();
        String result = solution.longestPalindrome("babRv1vRad");
        assertEquals("Rv1vR", result);
    }

    @Test
    void test2() {
        Solution0005 solution = new Solution0005();
        String result = solution.longestPalindrome("babkkkkkkad");
        assertEquals("kkkkkk", result);
    }


    @Test
    void test3() {
        Solution0005 solution = new Solution0005();
        String result = solution.longestPalindrome("b");
        assertEquals("b", result);
    }

    @Test
    void test4() {
        Solution0005 solution = new Solution0005();
        String result = solution.longestPalindrome("bd");
        assertEquals("b", result);
    }

}