package com.swb.binaryTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author fangsanpi
 * @date: 2019/10/1
 */
public class BinaryTreeByZigZag {
    public void printByZigZag(Node head) {
        if (head == null) {
            return;
        }
        Deque<Node> dq = new LinkedList<Node>();
        int level = 1;
        boolean lr = true;
        Node last = head;
        Node nLast = null;
        dq.offerFirst(head);
        printLevelAndOrientation(level++, lr);
        while (!dq.isEmpty()) {
            if (lr) {
                head = dq.pollFirst();
                if (head.left != null) {
                    nLast = nLast == null ? head.left : nLast;
                    dq.offerLast(head.left);
                }
                if (head.right != null) {
                    nLast = nLast == null ? head.right : nLast;
                    dq.offerLast(head.right);
                }
            } else {
                head = dq.pollLast();
                if (head.right != null) {
                    nLast = nLast == null ? head.right : nLast;
                    dq.offerFirst(head.right);
                }
                if (head.left != null) {
                    nLast = nLast == null ? head.left : nLast;
                    dq.offerFirst(head.left);
                }
            }
            System.out.print(head.value + " ");
            if (head == last && !dq.isEmpty()) {
                lr = !lr;
                last = nLast;
                nLast = null;
                System.out.println();
                printLevelAndOrientation(level++, lr);
            }
        }
        System.out.println();

    }
    public void printLevelAndOrientation(int level, boolean lr) {
        System.out.print("Level " + level + " from ");
        System.out.print(lr ? "left to right: " : "right to left: ");
    }
}
