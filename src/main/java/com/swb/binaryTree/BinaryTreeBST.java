package com.swb.binaryTree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fangsanpi
 * @date: 2019/10/1
 */
public class BinaryTreeBST {
    public int bstTopSize2(Node head) {
        Map<Node, Record> map = new HashMap<Node, Record>();
        return posOrder(head, map);
     }

    /**
     * 返回二叉树的最大拓扑结构的大小
     * @param h
     * @param map
     * @return
     */
     public int posOrder(Node h, Map<Node, Record> map) {
        if (h == null) {
            return 0;
        }
        int ls = posOrder(h.left, map);
        int rs = posOrder(h.right, map);
        modifyMap(h.left, h.value, map, true);
        modifyMap(h.right, h.value, map, false);
        Record lr = map.get(h.left);
        Record rr = map.get(h.right);
        int lbst = lr == null ? 0 : lr.l + lr.r + 1;
        int rbst = rr == null ? 0 : rr.l + rr.r + 1;
        map.put(h, new Record(lbst, rbst));
        return Math.max(lbst + rbst + 1, Math.max(ls, rs));
     }

     public int modifyMap(Node n, int v, Map<Node, Record>m, boolean s) {
         if (n == null || (!m.containsKey(n))) {
             return 0;
         }
         Record r = m.get(n);
         if ((s && n.value > v) || ((!s) && n.value < v)) {
             m.remove(n);
             return r.l + r.r + 1;
         } else {
             int minus = modifyMap(s ? n.right : n.left, v, m, s);
             if (s) {
                 r.r = r.r - minus;
             } else {
                 r.l = r.l - minus;
             }
             m.put(n, r);
             return minus;
         }
     }

}
