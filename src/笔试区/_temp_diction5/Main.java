package 笔试区._temp_diction5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //
        // System.out.println(5555555550l%90);
        int n = sc.nextInt();
        int cnt_5 = 0;
        //int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            if (num == 5) cnt_5++;
            //arr[i] = num;
        }

        String ans = find(n,cnt_5);

        System.out.println(-1);

    }

    private static String find(int n, int cnt_5) {
        if (n == 0)return null;
        if (n==cnt_5)return f(n,cnt_5);

        String ans = f(n,cnt_5);
        if (ans!=null){
            return ans;
        }

        String ans1 = find(n-1,cnt_5);
        String ans2 = find(n-1,cnt_5-1);
        if (ans1!= null && ans2!=null){
            if (ans1.compareTo(ans2)>=0){
                return ans1;
            }else {
                return ans2;
            }
        }

        if (ans1 != null)return ans1;
        if (ans2 != null)return ans2;
        return null;
    }

    private static String f(int n,int cnt_5){
        if (n == cnt_5){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(5);
            }
            if (Long.parseLong(sb.toString()) % 90 == 0){
                return sb.toString();
            }else return null;
        }

        int len = pow(2, n) - 1;
        StringBuilder sb = new StringBuilder();

        for (int i = len; i >= 0; i--) {
            if (check(i, cnt_5)) {
                //String s = String.valueOf(i);
                String s = Integer.toString(i,2);
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) == '1') {
                        sb.append(5);
                    } else {
                        sb.append(0);
                    }
                }
                long cur = Long.parseLong(sb.toString());
                //long p = cur%90;
                if ((cur % 90) == 0){
                    //System.out.println(sb.toString());
                    return sb.toString();
                }
                sb = new StringBuilder();
            }
        }

        return null;

    }

    private static boolean check(int i, int cnt_5) {
        int cnt = 0;
        while (i != 0) {
            if ((i & 1) == 1) {
                cnt++;
            }
            i >>>= 1;
        }
        return cnt == cnt_5;
    }

    public static int pow(int num, int n) {
        int result = 1;
        int k = num;
        while (n != 0) {
            if ((n & 1) == 1) {
                result *= k;
            }
            k *= k;

            n >>>= 1;
        }

        return result;
    }
}
