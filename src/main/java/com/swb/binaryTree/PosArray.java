package com.swb.binaryTree;

import java.util.HashMap;

/**
 * @author fangsanpi
 * @date: 2019/10/6
 */
public class PosArray {
    public int[] getPosArray(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }
        int len = pre.length;
        int[] pos = new int[len];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < len; i++) {
            map.put(in[i], i);
        }
        setPos(pre, 0, len - 1, in, 0, len - 1, pos, len - 1, map);
        return pos;
    }

    public int setPos(int[] p, int pi, int pj, int[] n, int ni, int nj, int[] s, int si, HashMap<Integer, Integer> map) {
        if (pi > pj) {
            return si;
        }
        s[si--] = p[pi];
        int i = map.get(p[pi]);
        si = setPos(p, pj - nj + i, pj, n, i + 1, nj, s, si, map);
        return setPos(p, pi + 1, pi + i - ni, n, ni, i - 1,s, si, map);
    }
}
