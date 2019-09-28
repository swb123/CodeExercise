package com.swb.binaryTree;

/**
 * @author fangsanpi
 * @date: 2019/9/28
 */
class ReturnType {
    public Node maxBSTHead;
    public int maxBSTSize;
    public int min;
    public int max;

    public ReturnType(Node maxBSTHead, int maxBSTSize, int min, int max) {
        this.maxBSTHead = maxBSTHead;
        this.maxBSTSize = maxBSTSize;
        this.min        = min;
        this.max        = max;
    }
}
public class BinaryTreeMaxSub {
    public ReturnType process(Node x) {
        if (x == null) {
            return new ReturnType(null,0, Integer.MAX_VALUE,Integer.MIN_VALUE);
        }
        ReturnType lData = process(x.left);
        ReturnType rData = process(x.right);
        int min = Math.min(x.value, Math.min(lData.min, rData.min));
        int max = Math.max(x.value,Math.max(lData.max, rData.max));
        int maxBSTSize = Math.max(lData.maxBSTSize, rData.maxBSTSize);
        Node maxBSTHead = lData.maxBSTSize >= rData.maxBSTSize ? lData.maxBSTHead : rData.maxBSTHead;
        if (lData.maxBSTHead == x.left && rData.maxBSTHead == x.right && x.value > lData.max && x.value < rData.min) {
            maxBSTSize = lData.maxBSTSize + rData.maxBSTSize + 1;
            maxBSTHead = x;
        }
        return new ReturnType(maxBSTHead, maxBSTSize, min, max);

    }
}
