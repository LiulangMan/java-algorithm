package 笔试区._temp_diction_6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


class Node {
    Node left;
    Node right;
    int val;

    public Node(Node left, Node right, int val) {
        this.left = left;
        this.right = right;
        this.val = val;
    }

    public Node(int val) {
        this.val = val;
    }
}

public class Main1 {
    private static int cnt = 0;//统计

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();

        HashMap<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            String ch = sc.next();
            int cho = sc.nextInt();//孩子val

            if (!map.containsKey(val)) {
                Node p = new Node(val);//加入
                map.put(val, p);
            }

            Node p = map.get(val);
            if (ch.equals("left")) {
                if (map.containsKey(cho)) {
                    p.left = map.get(cho);
                } else {
                    Node kind = new Node(cho);
                    map.put(cho, kind);
                    p.left = kind;
                }
            }

            if (ch.equals("right")) {
                if (map.containsKey(cho)) {
                    p.right = map.get(cho);
                } else {
                    Node kind = new Node(cho);
                    map.put(cho, kind);
                    p.right = kind;
                }
            }

        }
        f(map.get(1));
        System.out.println(cnt);
    }

    //递推算法
    private static void f(Node node) {
        if (isY(node))return;

        if (check(node))cnt++;

        if (node.left!=null)f(node.left);
        if (node.right!=null)f(node.right);
    }


    private static boolean check(Node node){
        //检查符合条件的樱桃
        if (isY(node))return false;

        return node.left!=null && node.right!=null && isY(node.left) && isY(node.right);
    }

    private static boolean isY(Node p) {
        return p.left == null && p.right == null;
    }
}
