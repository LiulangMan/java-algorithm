package 笔试区._temp_diction;

import java.util.Scanner;

//问题描述
//        　　儿童节那天有K位小朋友到小明家做客。小明拿出了珍藏的巧克力招待小朋友们。
//        　　小明一共有N块巧克力，其中第i块是Hi x Wi的方格组成的长方形。
//
//
//        　　为了公平起见，小明需要从这 N 块巧克力中切出K块巧克力分给小朋友们。切出的巧克力需要满足：
//
//
//        　　1. 形状是正方形，边长是整数
//        　　2. 大小相同
//
//
//        　　例如一块6x5的巧克力可以切出6块2x2的巧克力或者2块3x3的巧克力。
//
//
//        　　当然小朋友们都希望得到的巧克力尽可能大，你能帮小Hi计算出最大的边长是多少么？
//        输入格式
//        　　第一行包含两个整数N和K。(1 <= N, K <= 100000)
//        　　以下N行每行包含两个整数Hi和Wi。(1 <= Hi, Wi <= 100000)
//        　　输入保证每位小朋友至少能获得一块1x1的巧克力。
//        输出格式
//        　　输出切出的正方形巧克力最大可能的边长。
//        样例输入
//        2 10
//        6 5
//        5 6
//        样例输出
//        2
public class _切巧克力 {
    public static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[][] arr = new int[N][2];
        int max = 0;
        int result = 1;
        for (int i = 0; i < N; i++) {
            arr[i][0] = sc.nextInt();//high
            arr[i][1] = sc.nextInt();//width
            if (arr[i][0] > max) max = arr[i][0];
            if (arr[i][1] > max) max = arr[i][1];
        }
        for (int j = max; j >= 1; j--) {
            for (int i = 0; i < N; i++) {
                ans = ans + f(arr[i][0], arr[i][1], j);
            }
            if (ans >= K){
                result = j;
                break;
            }
            ans = 0;
        }
        System.out.println(result);
    }

    private static int f(int high, int width, int l) {
        int min = high < width ? high : width;
        if (l>min)return 0;
        return (width/l)*(high/l);
    }
}
