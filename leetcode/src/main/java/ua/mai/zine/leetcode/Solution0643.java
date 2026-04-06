package ua.mai.zine.leetcode;

/***
 * 643. Maximum Average Subarray I - https://leetcode.com/problems/maximum-average-subarray-i/description/
 */
class Solution0643 {

    public double findMaxAverage(int[] nums, int k) {
        if (nums.length < k)
            return Double.MIN_VALUE;

        double maxAvr;
        long sum = 0;

        for (int i = 0; i < k; i++){
            sum = sum + nums[i];
        }
        maxAvr = 1.0 * sum/k;

        for (int i = 1; i < nums.length - k + 1; i++) {
            sum = sum - nums[i - 1] + nums[i + k - 1];
            if (maxAvr < 1.0 * sum/k)
                maxAvr = 1.0 * sum/k;
        }

        return maxAvr;
    }

    public static void main(String[] args) {
        Solution0643 s = new Solution0643();
    }

}