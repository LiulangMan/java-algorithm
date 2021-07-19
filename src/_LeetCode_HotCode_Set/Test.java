package _LeetCode_HotCode_Set;


import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import org.omg.CORBA.OBJ_ADAPTER;
import sun.misc.Unsafe;

import java.io.*;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.util.*;

class Tree {
    int val;
    Tree left;
    Tree right;

    public Tree() {
    }

    public Tree(int val) {
        this.val = val;
    }

    public Tree(int val, Tree left, Tree right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Test {
    public static void main(String[] args) {

        Tree root = new Tree(1);
        root.left = new Tree(3);
        root.left.left = new Tree(4);
//        root.left.right = new Tree(5);
        root.right = new Tree(4);
        root.right.left = new Tree(7);
        root.right.right = new Tree(9);

        //prefix_order(root);
        mid_order(root);


    }

    private static void mid_order(Tree root) {

    }

    private static void prefix_order(Tree root) {
        if (root == null) return;

        Stack<Tree> stack = new Stack<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            Tree thisTree = stack.pop();
            System.out.print(thisTree.val + " ");
            if (thisTree.right != null) stack.push(thisTree.right);
            if (thisTree.left != null) stack.push(thisTree.left);
        }

    }

}
