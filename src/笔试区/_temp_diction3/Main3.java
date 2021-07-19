package 笔试区._temp_diction3;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(f(s));
    }

    private static String f(String s) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '_') cnt++;
            else {
                if (cnt == 0) {
                    sb.append(s.charAt(i));
                } else {
                    sb.append('_');
                    sb.append(s.charAt(i));
                    cnt = 0;
                }
            }
        }

        return sb.charAt(0)=='_'?sb.substring(1):sb.toString();
    }
}
