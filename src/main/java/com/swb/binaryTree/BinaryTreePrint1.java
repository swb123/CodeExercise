package com.swb.binaryTree;

/**
 * 直观地打印二叉树--打印结果顺时针旋转90度即为二叉树
 * @author fangsanpi
 * @date: 2019/9/25
 */
public class BinaryTreePrint1 {
    public void printTree(Node head) {
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    /**
     * 先打印右子树的右节点，再根节点，后左子树
     *
     * @param head
     * @param height
     * @param to
     * @param len
     */
    public void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }
    public String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }


}
