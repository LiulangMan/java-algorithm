package 笔试区._temp_diction_7;

import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();
            int sum = 0;
            HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
            for (int j = 0; j < m; j++) {
                int ii = sc.nextInt();
                int jj = sc.nextInt();
                int kk = sc.nextInt();

                sum += kk;

                if (map.containsKey(ii)) {
                    map.get(ii).add(jj);
                } else {
                    HashSet<Integer> list = new HashSet<>();
                    list.add(jj);
                    list.add(ii);
                    map.put(ii, list);
                }

                if (map.containsKey(jj)) {
                    map.get(jj).add(ii);
                } else {
                    HashSet<Integer> list = new HashSet<>();
                    list.add(ii);
                    list.add(jj);
                    map.put(jj, list);
                }

                //全添加
                map.get(ii).addAll(map.get(jj));
                map.get(jj).addAll(map.get(ii));

            }

            if ((1.0*sum) / m > (1.0*k)) ans.add("No");
            else {
                ans.add(f(map, n));
            }
        }

        for (String a : ans) {
            System.out.println(a);
        }

    }

    private static String f(HashMap<Integer, HashSet<Integer>> map, int n) {
        //连通性判断

        for (int i = 1; i <= n; i++) {
            if (map.get(i).size() != n) {
                return "No";
            }
        }

        return "Yes";
    }
}
