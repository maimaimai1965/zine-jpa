package ua.mai.zine.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Solution0007Test {


    @Test
    void test1() {
        Solution0007 solution = new Solution0007();
        int result = solution.reverse(-2147483648);
        assertEquals(0, result);
    }

    @Test
    void test2() {
        Solution0007 solution = new Solution0007();
        int result = solution.reverse(1534236469);
        assertEquals(0, result);
    }


    @Test
    void test3() {
        Solution0007 solution = new Solution0007();
        int result = solution.reverse(10);
        assertEquals(1, result);
    }

    @Test
    void test4() {
        Solution0007 solution = new Solution0007();
        int result = solution.reverse(-123);
        assertEquals(-321, result);
    }

    @Test
    void test5() {
        Solution0007 solution = new Solution0007();
        int result = solution.reverse(3456678);
        assertEquals(8766543, result);
    }

    @Test
    void test6() {
        Solution0007 solution = new Solution0007();
        int result = solution.reverse(-67878);
        assertEquals(-87876, result);
    }

}