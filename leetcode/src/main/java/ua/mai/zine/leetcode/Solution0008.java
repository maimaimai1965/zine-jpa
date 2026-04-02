package ua.mai.zine.leetcode;

import java.util.HashMap;
import java.util.Map;

/***
 * 8. String to Integer (atoi) - https://leetcode.com/problems/string-to-integer-atoi/description/
 */
class Solution0008 {

    public int myAtoi2(String s) {
        boolean positive = true;
        boolean definedSign = false;
        boolean startedNum = false;
        long value = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (startedNum)
                    break;
                if (definedSign)
                    return 0;
                continue;
            }
            if (c == '+' || c == '-') {
                if (startedNum || definedSign)
                    break;
                positive = c == '+';
                definedSign = true;
                continue;
            }
            if (c>='0' && c<='9') {
                definedSign = true;
                startedNum = true;
                if (c=='0' && !startedNum) {
                    continue;
                }
                value = value * 10 + (c - '0');

                long currValue = (positive ? 1 : -1 ) * value;
                if (positive && currValue > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
                if (!positive && currValue < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }


            } else {
                if (startedNum)
                    break;
                return 0;
            }
        }

        value = (positive ? 1 : -1 ) * value;

        return Long.valueOf(value).intValue();
    }

    public int myAtoi(String s) {
        int i = 0, n = s.length();

        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        long result = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i) - '0');

            if (sign * result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign * result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            i++;
        }

        return (int)(sign * result);
    }

    public static void main(String[] args) {
        Solution0008 s = new Solution0008();
        s.myAtoi("   +0 123");
        s.myAtoi("0-1");
        s.myAtoi("3.14159");
        s.myAtoi("-91283472332");
        s.myAtoi("1337c0d3");
        s.myAtoi("words and 987");
        s.myAtoi("42");
        s.myAtoi("-042");
        s.myAtoi("1337c0d3");
        s.myAtoi("0-1");
    }

}