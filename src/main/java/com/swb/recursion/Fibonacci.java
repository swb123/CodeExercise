package com.swb.recursion;

/**
 * @author fangsanpi
 * @date: 2020/1/2
 */
public class Fibonacci {
    /**
     * O(2^n)
     * @param n
     * @return
     */
    public int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    /**
     * O(N)
     * @param n
     * @return
     */
    public int f2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int temp = 0;
        int pre = 1;
        int res = 1;
        for(int i = 3; i <= n; i++) {
            temp = res;
            res = res + pre;
            pre = temp;
        }
        return res;
    }

    public int[][] matrixPower(int[][] m, int p) {
        if (m == null || m.length == 0 ||  m[0].length == 0 && p == 0) {
            return m;
        }
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            res[i][i]= 1;
        }
        int[][] temp = m;
        for (; p != 0 ; p >>= 1) {
            if ((p & 1) != 0) {
                res = mutliMartix(res, temp);
            }
            temp = mutliMartix(temp, temp);
        }
        return res;
    }
    public int[][] mutliMartix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public int f3(int n ) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n-2);
        return res[0][0] + res[1][0];
    }

    public int s3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = {{1,1},{1,0}};
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    public int c3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int[][] base = {{1, 1, 1}, {0, 0, 1}, {1, 0, 0}};
        int[][] res = matrixPower(base, n - 3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }
}
