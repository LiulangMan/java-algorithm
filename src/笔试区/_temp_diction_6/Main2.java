package 笔试区._temp_diction_6;

import sun.nio.cs.ext.MacHebrew;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String src = sc.next();
        System.out.println(f(src));
    }

    private static int f(String src) {


        int max = 0;
        for (int i = 0; i < src.length(); i++) {
            for (int j = src.length(); j >= i+1; j--) {
                if (check(src.substring(i,j))){
                    return Math.max(max,j-i);
                }
            }
        }


        return max;
    }

    private static boolean check(String src) {
        int cnt_a = 0;
        int cnt_b = 0;
        int cnt_c = 0;
        int cnt_x = 0;
        int cnt_y = 0;
        int cnt_z = 0;
        for (int i = 0; i < src.length(); i++) {
            char ch = src.charAt(i);
            switch (ch) {
                case 'a':
                    cnt_a++;
                    break;
                case 'b':
                    cnt_b++;
                    break;
                case 'c':
                    cnt_c++;
                    break;
                case 'x':
                    cnt_x++;
                    break;
                case 'y':
                    cnt_y++;
                    break;
                case 'z':
                    cnt_z++;
                    break;
            }
        }

        return cnt_a % 2 == 0 && cnt_b % 2 == 0 && cnt_c % 2 == 0 && cnt_x % 2 == 0 && cnt_y % 2 == 0 && cnt_z % 2 == 0;
    }
}
