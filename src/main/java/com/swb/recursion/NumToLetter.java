package com.swb.recursion;

/**
 * 给定一个字符串str,str全部由数字字符组成，如果str中的某一个或相邻两个字符组成的字串值在1~26之间，
 * 则这个字串可以转换为一个字母。
 * 规定"1"转换为"A","2"转换为"B",.....
 * 写一个函数，求str由多少种不同的转换结果，并返回种数
 *
 * @author fangsanpi
 * @date: 2020/1/13
 */
public class NumToLetter {

    public int num1(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chs = str.toCharArray();
        return process(chs, 0);
    }

    public int process(char[] chs, int i) {
        if (i == chs.length) {
            return 1;
        }
        if (chs[i] == '0') {
            return 0;
        }
        int res = process(chs, i + 1);
        if (i + 1 < chs.length && (chs[i] - '0') * 10 + chs[i + 1] - '0' < 27) {
            res += process(chs, i + 2);
        }
        return res;
    }

    public int num2(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chs = str.toCharArray();
        int cur = chs[chs.length - 1] == '0' ? 0 : 1;
        int next = 1;
        int tmp = 0;
        for (int i = chs.length - 2; i >= 0; i--) {
            if (chs[i] == '0') {
                next = cur;
                cur = 0;
            } else {
                tmp = cur;
                if ((chs[i] - '0') * 10 + chs[i + 1] - '0' < 27) {
                    cur += next;
                }
                next = tmp;
            }
        }
        return cur;
    }
}
