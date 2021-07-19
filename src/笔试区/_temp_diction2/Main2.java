package 笔试区._temp_diction2;


import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(f(s, sc.nextInt()));
    }

    private static String f(String s, int k) {
        int size = 1;
        int lo = 0;
        TreeSet<String> set = new TreeSet<>();
        while (size <= s.length()) {
            while (lo + size <= s.length()) {
                set.add(s.substring(lo, lo + size));
                lo++;
            }
            lo = 0;
            size++;
        }

//        String[] list = new String[set.size()];
//        int index = 0;
//        for (String e : set) {
//            list[index++] = e;
//        }
//
//        Arrays.sort(list);
        int index = 1;
        for (String e : set) {
            if (index == k) return e;
            index++;
        }

        return null;
    }
}
