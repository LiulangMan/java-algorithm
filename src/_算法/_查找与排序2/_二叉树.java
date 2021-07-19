package _算法._查找与排序2;
/***
 * 二叉树数据结构:数组实现
 *
 *
 * ***/


public class _二叉树 {
    public static void main(String[] args) {
        int[] tree = new int[10];
        for (int i = 0; i < 10; i++) {
            tree[i] = i+1;
        }
        PreOrder(tree,0);//先序遍历
        System.out.println();
        MidOrder(tree,0);//中序遍历
        System.out.println();
        LastOrder(tree,0);//后序遍历
    }

    private static void LastOrder(int[] tree, int father) {
        if (father>=tree.length)return;

        LastOrder(tree,2*father+1);
        LastOrder(tree,2*father+2);
        System.out.print(tree[father]+" ");
    }

    private static void MidOrder(int[] tree, int father) {
        if (father>=tree.length)return;

        MidOrder(tree,2*father+1);
        System.out.print(tree[father]+" ");
        MidOrder(tree,2*father+2);
    }

    private static void PreOrder(int[] tree,int father) {
        if (father>=tree.length)return;

        System.out.print(tree[father]+" ");
        PreOrder(tree,2*father+1);
        PreOrder(tree,2*father+2);
    }
}
