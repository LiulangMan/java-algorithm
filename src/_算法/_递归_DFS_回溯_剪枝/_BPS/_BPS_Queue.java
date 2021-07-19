package _算法._递归_DFS_回溯_剪枝._BPS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }

}

public class _BPS_Queue {
    public static void main(String[] args) {

    }

    //按层遍历二叉树
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> p = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = pRoot;
        TreeNode left = null;
        TreeNode right = null;

        queue.add(root);

        //采用队列就可以BFS
        while (!queue.isEmpty()) {
            TreeNode r = queue.poll();
            if (r.left != null) queue.add(r.left);
            if (r.right != null) queue.add(r.right);
            list.add(r.val);
        }

        p.add(list);
        return p;

    }
}
