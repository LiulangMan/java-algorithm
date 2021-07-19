package 笔试区._ACM;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/14 11:31
 */
public class _拆弹专家 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            String s = sc.next();
            list.add(solve(s.toCharArray(), a, b));
        }

        for (int a : list) {
            System.out.println(a);
        }
    }

    private static int solve(char[] arr, int a, int b) {
        float limit = (a * 1F) / b;
        StringBuilder sb = new StringBuilder();

        //压缩1
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '0') {
                sb.append('0');
            } else if (i == arr.length - 1 || arr[i + 1] == '0') {
                sb.append('1');
            }
        }

        char[] zip = sb.toString().toCharArray();
        int result = 0;
        int cnt = 0;
        List<Integer> index = index(zip, '1');
        for (int i = 0; i < index.size() - 1; i++) {
            if (index.get(i + 1) - index.get(i) < limit) {
                result += (index.get(i + 1) - index.get(i) - 1) * b;
            } else {
                result += a;
            }
        }
        return result + a;
    }

    private static ArrayList<Integer> index(char[] zip, char c) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < zip.length; i++) {
            if (zip[i] == c) list.add(i);
        }
        return list;
    }
}
