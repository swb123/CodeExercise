package com.swb.recursion;

/**
 * 假设有排成一行的N个位置，记为1~N,N一定大于或等于2.开始时机器人再其中的M位置上(M一定是1~N中的一个)
 * 机器人可以往左走或者往右走，如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左走来到N-1位置，规定机器人必须走K步，最终来到P位置（P也一定是1~N中的一个）
 * 的方法有多少种。
 *
 * @author fangsanpi
 * @date: 2020/1/5
 */
public class Walk {
    /**
     *
     * @param N: 位置1~N,固定参数
     * @param cur：当前cur位置，可变参数
     * @param rest：还剩rest步没有走，可变参数
     * @param P：最终目标位置是P,固定参数
     * @return
     */
    public int walk(int N, int cur, int rest, int P) {
        if (rest == 0) {
            return cur == P ? 1 : 0;
        }
        if (cur == 1) {
            return walk(N, 2, rest - 1, P);
        }
        if (cur == N) {
            return walk(N, N-1, rest - 1, P);
        }
        return walk(N, cur+1, rest - 1, P) + walk(N, cur - 1, rest- 1, P);
    }
    public int ways1(int N, int M, int K, int P) {
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > M) {
            return 0;
        }
        return walk(N, M, K, P);
    }

    public int ways2(int N, int M, int K, int P) {
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        int[][] dp = new int[K + 1][N + 1];
        dp[0][P] = 1;
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                if (j == 1) {
                    dp[i][j] = dp[i - 1][2];
                } else if (j == N) {
                    dp[i][j] = dp[i - 1][N - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                }
            }
        }
        return dp[K][M];
    }

    public int way3(int N, int M, int K, int P) {
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        int[] dp = new int[N + 1];
        dp[P] = 1;
        for (int i = 1; i <= K; i++) {
            int leftUp = dp[1];
            for (int j = 1; j <= N; j++) {
                int temp = dp[j];
                if (j == 1) {
                    dp[j] = dp[j + 1];
                } else if (j == N) {
                    dp[j] = leftUp;
                } else {
                    dp[j] = leftUp + dp[j + 1];
                }
                leftUp = temp;
            }
        }
        return dp[M];
    }

}
