package 笔试区._temp_diction3;

import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        String ans = f(num);
        System.out.println(ans.substring(0, ans.length() - 1));
    }

    private static String f(int num) {
        if (num == 1) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= num; i++) {
            if (check(i) && num % i == 0) {
                sb.append(i);
                sb.append('*');
                sb.append(f(num / i));
                break;
            }
        }

        return sb.toString();
    }

    private static boolean check(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) return false;
        }

        return true;
    }
}
