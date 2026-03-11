package ua.mai.zine.leetcode;

/***
 * 7. Reverse Integer - https://leetcode.com/problems/reverse-integer/description/
 */
class Solution0007 {

    public int reverse(int x) {

        long y = x;
        y = y < 0 ? -y : y;
        double r = 0;

        long[] ar = new long[20];
        int i = -1;
        while (y > 0) {
            i++;
            ar[i] = y % 10;
            y = y / 10;
        }
        double koef = Math.pow(10, i);
        for (int j = 0; j <= i; j++) {
            r = r + ar[j] * koef;
            koef = koef / 10;
        }
        if (r > Integer.MAX_VALUE || r < Integer.MIN_VALUE) {
            y = 0;
        } else
            y = (int) r;

        int rev = (int) (x < 0 ? -y : y);
        System.out.println("" + x + " -> " + rev + "(long - " + (long) r + ")");
        return rev;
    }

    public static void main(String[] args) {
        Solution0007 s = new Solution0007();
        s.reverse(-2147483648);
        s.reverse(1534236469);
        s.reverse(10);
        s.reverse(-123);
        s.reverse(3456678);
        s.reverse(-67878);
    }

}