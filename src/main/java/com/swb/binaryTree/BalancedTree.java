package com.swb.binaryTree;

/**
 * @author fangsanpi
 * @date: 2019/10/3
 */
 class ReturnType1{
    public boolean isBalanced;
    public int height;

    public ReturnType1(boolean isBalanced, int height) {
        this.isBalanced = isBalanced;
        this.height = height;
    }
}
public class BalancedTree {
     public ReturnType1 process(Node head) {
         if (head == null) {
             return new ReturnType1(true, 0);
         }
         ReturnType1 leftData = process(head.left);
         ReturnType1 rightData = process(head.right);
         int height = Math.max(leftData.height, rightData.height) + 1;
         boolean isBalanced = leftData.isBalanced && rightData.isBalanced && Math.abs(leftData.height - rightData.height) < 2;
         return new ReturnType1(isBalanced, height);
     }

     public boolean isBalanced(Node head) {
         return process(head).isBalanced;
     }

}
