package ua.mai.zine.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Solution0643Test {

    Solution0643 solution = new Solution0643();

    @Test
    void test1() {
        double avg = solution.findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4);
        assertEquals(12.75, avg);
    }


}