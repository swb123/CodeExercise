package com.swb.binaryTree;

/**
 * @author fangsanpi
 * @date: 2019/10/6
 */
public class TreeNode {
    public int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }
    public int bs(Node node, int l, int h) {
        if (l == h) {
            return 1;
        }
        if (mostLeftLevel(node.right, l + 1) == h) {
            return (1 << (h -1) + bs(node.right, l + 1,h));
        } else {
            return (1 << (h - l - 1) + bs(node.left, l + 1, h));
        }
    }
    public int mostLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }
}
