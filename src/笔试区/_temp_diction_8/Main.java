package 笔试区._temp_diction_8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        f(s);
    }

    private static void f(String s) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(s);
        ArrayList<Integer> list = new ArrayList<>();
        while (matcher.find()){
            String src = matcher.group();
            if (src.length()!=4)continue;
            int ans = Integer.parseInt(src);
            if (ans>=1000 && ans <=3999){
                list.add(ans);
            }
        }

        //Collections.sort(list);
        for (int e:list) {
            System.out.print(e+" ");
        }
    }
}
