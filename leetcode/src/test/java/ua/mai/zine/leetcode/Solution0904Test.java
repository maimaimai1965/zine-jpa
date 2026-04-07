package ua.mai.zine.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Solution0904Test {

    Solution0904 solution = new Solution0904();

    @Test
    void test1() {
        int sum = solution.totalFruit(new int[]{1,2,3,2,2});
        assertEquals(4, sum);
    }

    @Test
    void test2() {
        int sum = solution.totalFruit(new int[]{0,1,2,2});
        assertEquals(3, sum);
    }

    @Test
    void test3() {
        int sum = solution.totalFruit(new int[]{1,2,1});
        assertEquals(3, sum);
    }

}