package _算法._查找与排序1;

import java.util.Scanner;

//数学模型： y = f(n-1) + f（n-2） + f(n-3)  -> 参考 斐波拉契解法

public class _小白上楼梯 {
    private static int ans = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        findkind(n-1);  //1步
        findkind(n-2);  //2步
        findkind(n-3);  //3步

        System.out.println(ans);
    }

    private static void findkind(int n) {
        if(n == 0 ){
            ans++;
            return;
        }
        if(n < 0)return;
        findkind(n-1);
        findkind(n-2);
        findkind(n-3);
    }
}
