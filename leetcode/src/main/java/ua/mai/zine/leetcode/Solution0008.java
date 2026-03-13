package ua.mai.zine.leetcode;

import java.util.HashMap;
import java.util.Map;

/***
 * 7. Reverse Integer - https://leetcode.com/problems/reverse-integer/description/
 */
class Solution0008 {

    public int myAtoi(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int[] ar = new int[20];
        int i = -1;
        boolean positive = true;
        boolean readSign = false;
        boolean readFirstN = false;
        boolean readFirstNot0 = false;
        int result = 0;

        Map<Character, Integer> map = new HashMap();
        map.put('0',0);
        map.put('1',1);
        map.put('2',2);
        map.put('3',3);
        map.put('4',4);
        map.put('5',5);
        map.put('6',6);
        map.put('7',7);
        map.put('8',8);
        map.put('9',9);

        lab:
        for (int j = 0; j<s.length(); j++) {
            char ch = s.charAt(j);
            switch (ch) {
                case ' ':
                    if (readSign || readFirstN)
                        break;
                    else
                        continue;
                case '-':
                    if (!readSign) {
                        readSign = true;
                        positive = false;
                        continue;
                    } else {
                        i = -1;
                        break lab;
                    }
                case '+':
                    if (!readSign) {
                        readSign = true;
                        continue;
                    } else {
                        i = -1;
                        break lab;
                    }
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
                    readSign = true;
                    if (ch == '0') {
                        if (!readFirstNot0)
                            continue;
                    } else {
                        readFirstNot0 = true;
                    }
                    i++;
                    ar[i] = map.get(Character.valueOf(ch));
                    continue;
                default:
                    break lab;
            }
        }

        long v = 0;
        if (i >= 0) {
            long koef = 1;
            for (int j = i; j >= 0; j--) {
                v = v + koef * ar[j];
                koef= koef* 10;
            }
            v = (positive) ? v : -v;
            if (v < Integer.MIN_VALUE)
                result = Integer.MIN_VALUE;
            else
            if (v > Integer.MAX_VALUE)
                result = Integer.MAX_VALUE;
            else
                result = (int)v;
        }

        System.out.println("" + s + " -> " + result);
        return result;
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