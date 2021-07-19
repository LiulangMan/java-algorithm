package 笔试区._temp;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//题目描述：
//        小美是美团仓库的管理员，她会根据单据的要求按顺序取出仓库中的货物，每取出一件货物后会把剩余货物重新堆放，使得自己方便查找。已知货物入库的时候是按顺序堆放在一起的。
//        如果小美取出其中一件货物，则会把货物所在的一堆物品以取出的货物为界分成两堆，这样可以保证货物局部的顺序不变。
//
//        已知货物最初是按1~n的顺序堆放的，每件货物的重量为w_i,小美会根据单据依次不放回的取出货物。请问根据上述操作，小美每取出一件货物之后，重量和最大的一堆货物重量是多少？
//
//
//
//        输入描述
//        输入第一行包含一个正整数n，表示货物的数量。(1<=n,m<=50000)
//
//        输入第二行包含n个正整数，表示1~n号货物的重量w_i。(1<=w_i<=100)
//
//        输入第三行有n个数，表示小美按顺序取出的货物的编号，也就是一个1~n的全排列。
//
//        输出描述
//        输出包含n行，每行一个整数，表示每取出一件货物以后，对于重量和最大的一堆货物，其重量和为多少。

class Bind {
    int left;
    int right;
    int w;

    Bind(int left, int right, int w) {
        this.left = left;
        this.right = right;
        this.w = w;
    }

}

public class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Bind[] binds = new Bind[n];//标记每个节点的左右两端
        int[] tacks = new int[n];
        Map<Integer, Boolean> rec = new HashMap<>();//记录是否被取走
        for (int i = 0; i < n; i++) {
            binds[i] = new Bind(0, n - 1, sc.nextInt());
            rec.put(i, true);//表示没被取走
        }

        for (int i = 0; i < n; i++) {
            tacks[i] = sc.nextInt();
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = 0;
            int take = tacks[i] - 1;
            int temp_max = 0;
            int temp_left = binds[0].left;
            int temp_right = binds[0].right;
            rec.put(take, false);
            for (int j = 0; j < take; j++) {
                if (!rec.get(j)) continue;
                if (binds[j].left == temp_left && binds[j].right == temp_right) {
                    //同堆
                    temp_max += binds[j].w;

                } else {
                    //非同堆
                    //更新max
                    if (temp_max > max) max = temp_max;

                    //更新判别切点
                    temp_left = binds[j].left;
                    temp_right = binds[j].right;

                    //计数另一堆
                    temp_max = binds[j].w;

                }
                //更新切点
                if (binds[j].right >= take) {
                    binds[j].right = take - 1;
                }
            }

            binds[take].left = take;
            binds[take].right = take;
            //max = Math.max(binds[take].w, max);

            if (take + 1 < n) {
                temp_left = binds[take + 1].left;
                temp_right = binds[take + 1].right;
            }
            if (temp_max > max) max = temp_max;
            temp_max = 0;


            for (int j = take + 1; j < n; j++) {
                if (!rec.get(j)) continue;
                if (binds[j].left == temp_left && binds[j].right == temp_right) {
                    //同堆
                    temp_max += binds[j].w;

                } else {
                    //非同堆

                    //更新max
                    if (temp_max > max) max = temp_max;

                    //更新判别切点
                    temp_left = binds[j].left;
                    temp_right = binds[j].right;


                    //计数另一堆
                    temp_max = binds[j].w;

                }

                //更新切点
                if (binds[j].left <= take) {
                    binds[j].left = take + 1;
                }

            }

            System.out.println(max);
        }
    }
}
