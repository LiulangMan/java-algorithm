package 笔试区._temp_diction_8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] list = sc.nextLine().split(" ");

        ArrayList<String> ans = new ArrayList<>();

        ArrayList<String> prefix = new ArrayList<>();
        prefix.add("on");
        prefix.add("at");
        prefix.add("in");
        prefix.add("by");

        for (int i = 0; i < list.length; i++) {
            if (check(list[i])) {
                if (i - 1 >= 0 && prefix.contains(list[i-1]))
                    ans.add(list[i]);
            }
        }

        for (String e : ans) {
            System.out.print(e + " ");
        }
    }

    private static boolean check(String s) {
        try {
            if (s.charAt(s.length()-1) == ',' || s.charAt(s.length()-1) == '.'){
                s = s.substring(0,s.length()-1);
            }

            int ans = Integer.parseInt(s);
            return 1000 <= ans && ans <= 3999;
        } catch (Exception e) {
            return false;
        }
    }
}
