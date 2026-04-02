package ua.mai.zine.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Solution0008Test {

    @Test
    void test1() {
        Solution0008 solution = new Solution0008();
        int result = solution.myAtoi("42");
        System.out.println(result);
        assertEquals(42, result);
    }

    @Test
    void test2() {
        Solution0008 solution = new Solution0008();
        int result = solution.myAtoi(" -042");
        System.out.println(result);
        assertEquals(-42, result);
    }

    @Test
    void test3() {
        Solution0008 solution = new Solution0008();
        int result = solution.myAtoi("1337c0d3");
        System.out.println(result);
        assertEquals(1337, result);
    }

    @Test
    void test4() {
        Solution0008 solution = new Solution0008();
        int result = solution.myAtoi("0-1");
        System.out.println(result);
        assertEquals(0, result);
    }

    @Test
    void test5() {
        Solution0008 solution = new Solution0008();
        int result = solution.myAtoi("words and 987");
        System.out.println(result);
        assertEquals(0, result);
    }

    @Test
    void test6() {
        Solution0008 solution = new Solution0008();
        int result = solution.myAtoi("4193 with words");
        System.out.println(result);
        assertEquals(4193, result);
    }

    @Test
    void test7() {
        Solution0008 solution = new Solution0008();
        int result = solution.myAtoi("-91283472332");
        System.out.println(result);
        assertEquals(-2147483648, result);
    }

    @Test
    void test8() {
        Solution0008 solution = new Solution0008();
        int result = solution.myAtoi("21474836460");
        System.out.println(result);
        assertEquals(2147483647, result);
    }

    @Test
    void test9() {
        Solution0008 solution = new Solution0008();
        int result = solution.myAtoi("9223372036854775808");
        System.out.println(result);
        assertEquals(2147483647, result);
    }

    @Test
    void test10() {
        Solution0008 solution = new Solution0008();
        int result = solution.myAtoi("-5-");
        System.out.println(result);
        assertEquals(-5, result);
    }

    @Test
    void test11() {
        Solution0008 solution = new Solution0008();
        int result = solution.myAtoi("  +  413");
        System.out.println(result);
        assertEquals(0, result);
    }

}