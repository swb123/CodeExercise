package com.swb.recursion;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author fangsanpi
 * @date: 2020/1/8
 */
public class Envelop implements Comparator<Envelop> {
    private int len;
    private int wild;


    public Envelop() {
    }

    public Envelop(int len, int wild) {
        this.len = len;
        this.wild = wild;
    }



    @Override
    public int compare(Envelop o1, Envelop o2) {
        return o1.len == o2.len ? o1.len - o2.len : o2.wild - o1.wild;
    }

    public Envelop[] getSortedEnvelpes(int[][] matrix) {
        Envelop[] res = new Envelop[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            res[i] = new Envelop(matrix[i][0], matrix[i][0]);
        }
        Arrays.sort(res, new Envelop());
        return res;
    }

    public int maxEnvelpoe(int[][] matrix) {
        Envelop[] envelops = getSortedEnvelpes(matrix);
        int[] ends = new int[matrix.length];
        ends[0] = envelops[0].wild;
        int right = 0;
        int m = 0;
        int l = 0;
        int r = 0;
        for (int i = 1; i < envelops.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (envelops[m].wild < envelops[i].wild) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = envelops[i].wild;

        }
        return right + 1;
    }
}
