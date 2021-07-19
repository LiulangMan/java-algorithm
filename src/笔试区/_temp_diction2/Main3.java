package 笔试区._temp_diction2;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int num = sc.nextInt();
            System.out.println(f(num));
        }
    }

    private static int f(int num) {
        int max = 0;
        for (int i = 0; i < num / 2; i++) {
            int j = num - i;
            int temp = 0;
            String si = String.valueOf(i);
            String sj = String.valueOf(j);
            for (int k = 0; k < si.length(); k++) {
                temp += si.charAt(k) - '0';
            }
            for (int k = 0; k < sj.length(); k++) {
                temp += sj.charAt(k) - '0';
            }
            max = Math.max(temp, max);

        }
        return max;
    }
}
