package 笔试区._temp;


import java.util.Scanner;

public class Main5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int n = x + y;
        int[] v = new int[n];

        for (int i = 0; i < n; i++) {
            v[i] = sc.nextInt();
        }

        System.out.println(f(v, x, y));


    }

    private static String f(int[] v, int x, int y) {
        double ave = 0;
        int sum = 0;
        for (int i = 0; i < v.length; i++) {
            sum += v[i];
        }
        ave = (double) sum / v.length;

        //尽量让两组差不多
        StringBuilder sb = new StringBuilder();
        int sumx = 0;
        int sumy = 0;
        int contx = 0;
        int conty = 0;
        for (int i = 0; i < v.length; i++) {
            if (contx < x && ((double) sumx + v[i]) / x <= ave ) {
                sb.append('A');
                contx++;
            } else  if (conty < y && ((double) sumy + v[i]) / y <= ave){
                sb.append('B');
                conty++;
            }
        }
        return sb.toString();
    }
}
