package _数据结构._Tree;

import java.util.*;

public class _红黑树 {
    public static void main(String[] args) {


        RedBlackTree tree = new RedBlackTree(10);
        Scanner sc = new Scanner(System.in);
        int key = sc.nextInt();
        while (key != 0) {
            tree.insertKey(key);
            tree.levelOrder();
            key = sc.nextInt();
        }
        System.out.println("---------------------------");
        ArrayList<Integer> list = tree.integers();
        for (int e:list) {
            System.out.print(e+" ");
        }

        sc.close();
        System.out.println("程序结束");
    }

}

class RedBlackTree {

    private Node root;
    private int size;

    private class Node {
        int key;
        boolean color;//黑色为true
        Node left, right, parent;

        public Node(int key) {
            this.key = key;
        }
    }

    public RedBlackTree(int key) {
        this.root = new Node(key);
        root.color = true;
        this.size = 1;
    }

    //插入一个新的key

    /***
     * 红黑树的插入分六种情况，其中后三种与前三种对称
     *
     * 父节点为黑色：直接插入，红黑树未被破坏
     *
     * 父节点为红色:
     * 1，插入位置为左子树，且uncle为红色：
     *      将当前parent 和 uncle 变为黑色，grandparent 变为红色
     *      如果grandparent == root ,grandparent 变黑
     *
     * 2，父节点为左子树，插入位置为左子树，uncle为黑色或不存在：
     *      以grandparent为中心右旋
     *      变为情况1
     *
     * 3,父节点为左子树，插入位置为右子树，uncle为黑色或不存在：
     *      以parent为中心左旋
     *      变为情况2
     *
     *
     * ------------------------对称---------------------------
     *
     * 4，插入位置为右子树，且uncle为红色：
     *      将当前parent 和 uncle 变为黑色，grandparent 变为红色
     *      果grandparent == root ,grandparent 变黑
     *
     * 5，父节点为右子树，插入位置为右子树，uncle为黑色或不存在：
     *      以grandparent为中心左旋
     *      变为情况4
     *
     * 6，父节点为右子树，插入位置为左子树，uncle为黑色或不存在：
     *      以parent为中心右旋
     *      变为情况5
     *
     *
     * ***/
    public void insertKey(int key) {
        size++;
        insertKey(new Node(key), root);
    }

    //插入一个node
    private void insertKey(Node child, Node node) {
        if (child.key <= node.key) {
            if (node.left == null) {
                node.left = child;
                child.parent = node;
                fixTree(child);//修复树
            } else {
                insertKey(child, node.left);
            }
        } else {
            if (node.right == null) {
                node.right = child;
                child.parent = node;
                fixTree(child);
            } else {
                insertKey(child, node.right);
            }
        }
    }

    //检查并且修理树
    private void fixTree(Node node) {
        //如果父节点为空，一定是根节点，根节点不需要判断，
        //否则，父节点为红节点。继续检查
        while (node != root && !node.parent.color) {
            Node parent = node.parent;
            Node grandParent = parent.parent;
            Node uncle = null;

            //父节点为左节点的情况
            if (grandParent.left == parent) {
                uncle = grandParent.right;
                if (uncle == null || uncle.color) {
                    //第三种情况，先调整成情况二
                    //插入位置是右子树
                    if (parent.right == node) {
                        leftRotate(parent);
                        node = parent;
                    }
                    //情况二，进行一次旋转
                    //因为有可能经过情况3的处理，node 已经发生改变
                    node.parent.color = true;
                    grandParent.color = false;
                    //右旋转
                    rightRotate(grandParent);
                }

            } else {
                //父节点为右节点的情况
                uncle = grandParent.left;
                if (uncle == null || uncle.color) {
                    //第六种情况，先调整成情况五
                    //插入位置是左子树
                    if (parent.left == node) {
                        rightRotate(parent);
                        node = parent;
                    }
                    //情况五，进行一次旋转
                    node.parent.color = true;
                    grandParent.color = false;
                    leftRotate(grandParent);
                }
            }

            //情况一和四

            //只要将父辈节点都涂黑，grandparent涂红
            parent.color = true;
            if (uncle != null && !uncle.color) uncle.color = true;
            grandParent.color = false;

            //将grandparent 设置为node，重新进行循环检查
            node = grandParent;

        }
        root.color = true;//根节点颜色涂黑
    }

    private void rightRotate(Node node) {
        Node temp = node.left;//强引用
        if (temp != null) {
            //接收原左孩子的右孩子
            node.left = temp.right;
            if (temp.right != null) {
                temp.right.parent = node;
            }

            //重新设置新父节点
            if (root == node) root = temp;
            else if (node.parent.left == node) node.parent.left = temp;
            else node.parent.right = temp;

            //重新设置右孩子
            node.parent = temp;
            temp.right = node;
        }
    }

    private void leftRotate(Node node) {
        Node temp = node.right;
        if (temp != null) {
            node.right = temp.left;
            if (temp.left != null) {
                temp.left.parent = node;
            }


            if (root == node) root = temp;
            else if (node.parent.left == node) node.parent.left = temp;
            else node.parent.right = temp;

            node.parent = temp;
            temp.left = node;
        }
    }

    public void levelOrder() {
        //遍历
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.key + (node.color ? "black" : "red") + " ");

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }

    //查找
    public boolean exitKey(int target) {
        return search(target, root) != null;
    }

    private Node search(int target, Node node) {
        if (node == null) return null;

        if (target == node.key) return node;

        else if (target <= node.key) return search(target, node.left);
        else return search(target, node.right);
    }

    public int size() {
        return this.size;
    }


    //删除
    public boolean deleteKey(int target) {
        if (exitKey(target)) {
            size--;
            Node node = search(target, root);
            if (node.left == null && node.right == null) {
                //没有孩子，直接删除就好
                if (node.parent.left == node) node.parent.left = null;
                if (node.parent.right == node) node.parent.right = null;

                if (node.color) {
                    //如果是黑色，需要修复
                }

                //jvm进行GC
                node.left = node.right = node.parent = node = null;

            } else if (node.left != null && node.right != null) {
                //两个孩子
                //节点node有左右孩子节点，这时首先找到node节点的后继节点survivor， 这时又有两种情况：第一种情况是survivor是node的
                // 右孩子节点，第二种情况是survivor不是z的右孩子节点。当survivor不是node的右孩子时，这时需要把survivor节点从原来的
                // 地方剥离出来，然后替换掉节点node。并用survivor的右孩子x填补survivor的位置。
                //survivor不可能有左孩子节点，因为如果有左孩子节点则它的左孩子节点更有可能成为节点z的后继。因而survivor节点要么没有孩子节点
                //要么只有右孩子。
                Node survivor = survivor(node);
                if (survivor != null) {
                    if (survivor.parent == node) {
                        //survivor 是 node 右孩子
                        if (node.parent.left == node) node.parent.left = survivor;
                        if (node.parent.right == node) node.parent.right = survivor;
                        survivor.parent = node.parent;
                        survivor.left = node.left;
                        node.left.parent = survivor;

                        boolean black = survivor.color;
                        survivor.color = node.color;

                        if (black) {
                            //修复
                        }



                    } else {
                        //survivor 不是 node 右孩子
                        //用survivor的右孩子x填补survivor的位置
                        Node survivor_right  = survivor.right;

                        if (survivor_right != null){
                            if (survivor.parent.left == survivor)survivor.parent.left = survivor_right;
                            if (survivor.parent.right == survivor)survivor.parent.right = survivor_right;
                            survivor_right.parent = survivor.parent;

                        }
                        if (node.parent.left == node) node.parent.left = survivor;
                        if (node.parent.right == node) node.parent.right = survivor;
                        survivor.parent = node.parent;
                        survivor.left = node.left;
                        node.left.parent = survivor;

                        boolean black = survivor.color;
                        survivor.color = node.color;

                        if (black) {
                            //修复
                        }

                    }
                }

                //jvm进行GC
                node.left = node.right = node.parent = node = null;

            } else {
                //一个孩子
                Node survivor = node.left == null ? node.right : node.left;

                //孩子直接继承
                if (node.parent.left == node) node.parent.left = survivor;
                if (node.parent.right == node) node.parent.right = survivor;
                survivor.parent = node.parent;

                if (node.color) {
                    //修复
                }

                //jvm进行GC
                node.left = node.right = node.parent = node = null;
            }
            return true;
        } else return false;
    }

    /***
     * 寻找node的继承节点（比它大的最小一个）
     * ***/
    private Node survivor(Node node) {
        Node p = node;
        if (p == null) return null;


        //找第一个右节点的最后一个左节点
        if (p.right != null) {

            p = p.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        //找第一个向右的父节点
        if (p.parent != null) {
            while (p == p.parent.right) {
                p = p.parent;
            }

            return p;
        }

        //如果前两个条件都不满足，则是根节点
        else return null;
    }

    public ArrayList<Integer> integers(){
        ArrayList<Integer> list = new ArrayList<>();
        for (Node node = survivor(root); node != null; node = survivor(node)) {
            list.add(node.key);
        }
        return list;
    }

}
