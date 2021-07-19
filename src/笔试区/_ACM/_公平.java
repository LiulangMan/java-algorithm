package 笔试区._ACM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/14 9:48
 */
public class _公平 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            int num = sc.nextInt();
            int result = 0;
            for (int j = 0; j < num; j++) {
                String next = sc.next();
                if (next.equals("self")) continue;
                else if (next.equals("search")) {
                    result += 4;
                } else {
                    result += 2;
                }
            }
            list.add(result);
        }

        for (Integer integer : list) {
            System.out.println(integer);
        }

    }

}
