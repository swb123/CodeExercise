package com.swb.binaryTree;

/**
 * @author fangsanpi
 * @date: 2019/10/6
 */
public class MaxDistance {
    public ReturnType2 process(Node head) {
        if (head == null) {
            return new ReturnType2(0,0);
        }
        ReturnType2 leftData = process(head.left);
        ReturnType2 rightData = process(head.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        int maxDistance = Math.max(leftData.height + rightData.height + 1, Math.max(leftData.maxDistance,rightData.maxDistance));
        return new ReturnType2(maxDistance, height);
    }

    public int getMaxDistance(Node head) {
        return process(head).maxDistance;
    }
}
