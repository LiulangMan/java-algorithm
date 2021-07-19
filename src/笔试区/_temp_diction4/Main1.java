package 笔试区._temp_diction4;



class TreeNode {
   int val;
   TreeNode left;
  TreeNode right;
   TreeNode(int x) { val = x; }
 }

public class Main1 {

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null)return false;
        return f(root,root.val,sum);
    }

    public boolean f(TreeNode p,int current,int sum){
        if(current == sum && isYe(p)){
            return true;
        }
        if(isYe(p)) return false;
        int temp = current;
        boolean left_b = false;
        boolean right_b = false;
        if(p.left != null){

            temp += p.left.val;
            if(temp == sum && isYe(p.left))left_b = true;
            else left_b = f(p.left,temp,sum);
        }
        temp = current;
        if(left_b)return true;

        if(p.right != null){
            temp += p.right.val;
            if(temp == sum && isYe(p.right))right_b = true;
            else right_b =  f(p.right,temp,sum);
        }

        return right_b;

    }

    private boolean isYe(TreeNode p){
        return p.left == null && p.right == null;
    }
}