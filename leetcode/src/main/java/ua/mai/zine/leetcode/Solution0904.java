package ua.mai.zine.leetcode;

/***
 * 904. Fruit Into Baskets - https://leetcode.com/problems/fruit-into-baskets/description/
 */
class Solution0904 {


    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int lastFruit = -1, secondLastFruit = -1;
        int lastCount = 0, currMax = 0, max = 0;

        for (int i = 0; i < n; i++) {
            int fruit = fruits[i];

            if (fruit == lastFruit || fruit == secondLastFruit) {
                currMax++;
            } else {
                currMax = lastCount + 1;
            }

            if (fruit == lastFruit) {
                lastCount++;
            } else {
                lastCount = 1;
                secondLastFruit = lastFruit;
                lastFruit = fruit;
            }

            max = Math.max(max, currMax);
        }

        return max;
    }

    public int totalFruit2(int[] fruits) {
        if (fruits.length == 0)
            return 0;

        int sum = 0;

        int basket1 = -1;
        int basket2 = -1;
        int currSum = 0;

        for (int i = 0; i < fruits.length; i++) {
            if (fruits.length - i < currSum) {
                break;
            }
            for (int j = i; j < fruits.length; j++) {
                int fruit = fruits[j];
                if (fruit == basket1 ||
                        fruit == basket2) {
                    currSum++;
                    if (currSum > sum) {
                        sum = currSum;
                    };
                } else
                if (basket1 == -1) {
                    basket1 = fruit;
                    currSum++;
                    if (currSum > sum) {
                        sum = currSum;
                    };
                } else
                if (basket2 == -1) {
                    basket2 = fruit;
                    currSum++;
                    if (currSum > sum) {
                        sum = currSum;
                    };
                } else {
                    break;
                }
            }

            basket1 = -1;
            basket2 = -1;
            currSum = 0;
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution0904 s = new Solution0904();
    }

}