package com.swb.binaryTree;

import java.util.Stack;

/**
 * @author fangsanpi
 * @date: 2019/10/1
 */
public class TwoErrNodes {
    /**
     * 返回两个错误节点
     * @param head
     * @return
     */
    public Node[] getTwoErrNodes(Node head) {
        Node[] errs = new Node[2];
        if (head == null) {
            return errs;
        }
        Stack<Node> stack = new Stack<Node> ();
        Node pre = null;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (pre != null && pre.value > head.value) {
                    errs[0] = errs[0] == null ? pre : errs[0];
                    errs[1] = head;;
                }
                pre = head;
                head = head.right;
            }
        }
        return errs;
    }

    /**
     * 找到两个错误节点各自父节点
     * @param head
     * @param e1
     * @param e2
     * @return
     */
    public Node[] getTwoErrParents(Node head, Node e1, Node e2) {
        Node[] parents = new Node[2];
        if (head == null) {
            return parents;
        }
        Stack<Node> stack = new Stack<Node>();
        while(!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (head.left == e1 || head.right == e1) {
                    parents[0] = head;
                }
                if (head.left == e2 || head.right == e2) {
                    parents[1] = head;
                }
                head = head.right;
            }
        }
        return parents;
    }

    public Node recoverTree(Node head) {
        Node[] errs = getTwoErrNodes(head);
        Node[] parents = getTwoErrParents(head, errs[0], errs[1]);
        Node e1 = errs[0];
        Node e1P = parents[0];
        Node e1L = e1.left;
        Node e1R = e1.right;
        Node e2 = errs[1];
        Node e2P = parents[1];
        Node e2L = e2.left;
        Node e2R = e2.right;
        if (e1 == head) {
            // e1是头节点，e1是e2的头节点，此时e2只可能是e1的右孩子节点。
            if (e1 == e2P) {
                e1.left = e2L;
                e1.right = e2R;
                e2.right = e1;
                e2.left = e1L;
            } else if (e2P.left == e2) { // e1是头节点，e1不是e2的父节点，e2是e2P的左孩子节点
                e2P.left = e1;
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2L;
                e1.right = e2R;
            } else { // e1是头节点，e1不是e2的父节点，e2是e2P的右孩子节点
                e2P.right = e1;
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2L;
                e1.right = e2R;
            }
            head = e2;
        } else if (e2 == head) {
            if (e2 == e1P) {
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2;
                e1.right = e2R;
            } else if (e1P.left == e1) {
                e1P.left = e2;
                e1.left = e2L;
                e1.right = e2R;
                e2.left = e1L;
                e2.right = e1R;
            } else {
                e1P.right = e2;
                e1.left = e2L;
                e1.right = e2R;
                e2.left = e1L;
                e2.right = e1R;
            }
            head = e1;
        } else {
            if (e1 == e2P) {
                if (e1P.left == e1) {
                    e1P.left = e2;
                    e1.left = e2L;
                    e1.right = e2R;
                    e2.left = e1L;
                    e2.right = e1;
                } else {
                    e1P.right = e2;
                    e1.right = e2L;
                    e1.right = e2R;
                    e2.left = e1L;
                    e2.right = e1;
                }
            } else if (e2 == e1P) {
                if (e2P.left == e2) {
                    e2P.left = e1;
                    e2.left = e1L;
                    e2.right = e1R;
                    e1.left = e2;
                    e1.right = e2R;
                } else {
                    e2P.right = e1;
                    e2.left = e1L;
                    e2.right = e1R;
                    e1.left = e2;
                    e1.right = e2R;
                }
            } else {
                if (e1P.left == e1) {
                    if (e2P.left == e2) {
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.left = e2;
                        e2P.left = e1;
                    } else {
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.left = e2;
                        e2P.right = e1;
                    }
                } else {
                    if (e2P.left == e2) {
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.right = e2;
                        e2P.left = e1;
                    } else {
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.right = e2;
                        e2P.right = e1;
                    }
                }
            }
        }
        return head;
    }
}
