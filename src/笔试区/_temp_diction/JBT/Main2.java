package 笔试区._temp_diction.JBT;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x1 = sc.nextLong();
        long y1 = sc.nextLong();
        long z1 = sc.nextLong();
        long N = sc.nextLong();
        long xy = sc.nextLong();
        long xz = sc.nextLong();
        long yx = sc.nextLong();
        long yz = sc.nextLong();
        long zx = sc.nextLong();
        long zy = sc.nextLong();

        for (int i = 0; i < N; i++) {
            long x = x1, y = y1, z = z1;
            x += yx * y1 + zx * z1;
            y += xy * x1 + zy * z1;
            z += xz * x1 + yz * y1;

            x1 = x;
            y1 = y;
            z1 = z;

            x1 %= 1000000007;
            y1 %= 1000000007;
            z1 %= 1000000007;
        }

        System.out.print(x1 + " " + y1 + " " + z1);
    }
}
