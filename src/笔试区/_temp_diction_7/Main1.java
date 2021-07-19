package 笔试区._temp_diction_7;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        String src1 = sc.nextLine();
        String src = sc.nextLine();
        System.out.println(f(src, len));
    }

    private static String f(String src, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < src.length(); i += len) {
                sb.append(reverse(src.substring(i, Math.min(i + len, src.length()))));
        }
        return sb.toString();
    }

    private static String reverse(String src){
        return new StringBuilder(src).reverse().toString();
    }

}
