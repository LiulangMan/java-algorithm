package 笔试区._ACM;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/14 9:23
 */
public class _次方数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        List<BigInteger> list = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            int a = sc.nextInt();
            BigInteger b = new BigInteger(sc.next());
            BigInteger result = new BigInteger("0");
            BigInteger j = new BigInteger("1");
            for (; j.compareTo(b) < 0; j.add(BigInteger.ONE)) {
                BigInteger num = new BigInteger(j.toString());
                result = num.pow(a).add(result);
            }
            list.add(result);
        }

        for (BigInteger bigInteger : list) {
            System.out.println(bigInteger);
        }
    }
}
