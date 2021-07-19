package _算法._递归_DFS_回溯_剪枝;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/***
 * 给定n,输出n对括号的所有合法形式（不重复）
 *
 * ***/
public class _合法括号 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Set<String> ans = findway(n);
        System.out.println(ans.size());
        for (String eOfset : ans) {
            System.out.println(eOfset);
        }
    }

    private static Set<String> findway(int n) {

        Set<String> s_n = new HashSet<>();

        if (n == 1) {
            //出口
            s_n.add("()");
            return s_n;
        }

        Set<String> s_n_1 = findway(n - 1);
        for (String eOfsn_1 : s_n_1) {
            s_n.add("()" + eOfsn_1);//左边
            s_n.add(eOfsn_1 + "()");//右边
            s_n.add("(" + eOfsn_1 + ")");//外边
        }
        return s_n;
    }

    private static Set<String> findway2(int n) {
        //迭代形式

        Set<String> res = new HashSet<>();

        res.add("()");
        if (n == 1) return res;

        for (int i = 2; i <= n; i++) {
            Set<String> new_res = new HashSet<>();

            for (String e : res) {
                new_res.add("()" + e);
                new_res.add(e + "()");
                new_res.add("(" + e + ")");

                res = new_res;
            }
        }
        return res;
    }
}
