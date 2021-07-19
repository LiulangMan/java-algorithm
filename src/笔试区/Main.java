package 笔试区;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

//输入第一行包含两个正整数n，m，表示订单的数量和小美可以接的订单数量(1<=n,m<=10000)
//
//        接下来有n行，第i行表示i-1号订单的信息。每行有两个正整数v和w ，表示一个订单的跑腿价格和商品重量。(1<=v,w<=1000)
//
//        输出描述
//        输出包含m个1~n之间的正整数，中间用空格隔开，表示选择的订单编号。

class Bag {
    int v;
    int w;
    int id;

    Bag(int v, int w,int id) {
        this.v = v;
        this.w = w;
        this.id = id;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        //
        Bag[] bags = new Bag[n];

        for (int i = 0; i < n; i++) {
            int v = sc.nextInt();
            int w = sc.nextInt();
            bags[i] = new Bag(v, w,i+1);
        }

        //跑腿价格v，商品重量w kg，商品每重1kg，代购费用要加2元，
        Arrays.sort(bags, new Comparator<Bag>() {
            @Override
            public int compare(Bag o1, Bag o2) {
                int v1 = o1.v+o1.w*2;
                int v2 = o2.v+o2.w*2;
                return v2 - v1;
            }
        });
        int[] ids = new int[m];
        for (int i = 0; i < m; i++) {
            ids[i] = (bags[i].id);
        }

        Arrays.sort(ids);

        for (int i = 0; i < m; i++) {
            System.out.print(ids[i]+" ");
        }

    }
}
