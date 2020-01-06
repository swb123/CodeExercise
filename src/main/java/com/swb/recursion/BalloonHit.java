package com.swb.recursion;

/**
 *
 * @author fangsanpi
 * @date: 2020/1/6
 */
public class BalloonHit {
    /**
     * 打爆arr[L...R]范围上的所有气球，返回最大的分数
     * 假设arr[L-1]和arr[R+1] 一定没有被打爆
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L - 1] * arr[L] * arr[R + 1];
        }
        int max = Math.max(arr[L - 1] * arr[L] * arr[R + 1] + process(arr, L + 1, R), arr[L - 1] * arr[R] * arr[R + 1] + process(arr, L,R- 1));
        for (int i = L + 1; i < R; i++) {
            max = Math.max(max, arr[L - 1] * arr[i] * arr[R + 1] + process(arr, L, i - 1) + process(arr, i + 1, R));
        }
        return max;
    }

    public int maxConins1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int N = arr.length;
        int[] help = new int[N + 2];
        help[0] = 1;
        help[N + 1] = 1;
        for (int i = 0; i < N; i++) {
            help[i + 1] = arr[i];
        }
        return process(arr, 1, N);
    }

    public int maxCoins2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int N = arr.length;
        int[] help = new int[N + 2];
        help[0] = 1;
        help[N + 1] = 1;
        for (int i = 0; i < N; i++) {
            help[i + 1] = arr[i];
        }
        int[][] dp = new int[N + 2][N + 2];
        for (int i = 1; i <= N; i++) {
            dp[i][i] = help[i - 1] * help[i] * help[i + 1];

        }
        for (int L = N; L >= 1; L--) {
            for (int R = L + 1; R <= N; R++) {
                int finalL = help[L - 1] * help[L] * help[R + 1] + dp[L + 1][R];
                int finalR = help[L - 1] * help[R] * help[R+1] + dp[L][R-1];
                dp[L][R] = Math.max(finalL, finalR);
                for (int i = L + 1; i < R; i++) {
                    dp[L][R] = Math.max(dp[L][R], help[L - 1]* help[i]* help[R+1] + dp[L][i - 1]+ dp[i+1][R]);
                }
            }
        }
        return dp[1][N];
    }

}
