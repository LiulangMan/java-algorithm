package _算法._数学问题;

import java.util.ArrayList;
import java.util.Scanner;

/***
 *
 * 变种三进制：
 *
 *用天平称重时，我们希望用尽可能少的砝码组成尽可能重的重量，如果有无限个砝码，为1、3、9、27、81等3的指数幂
 * 神奇的是，任意一个大于0的整数都可以被表示出
 *
 *输入：合法整数
 *输出：称重方案
 *
 * 例：
 * in：5
 * out：9-3-1
 *
 *
 * 重量<1000000
 * ***/

public class _巧用进制 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x = sc.nextLong();
        printFind(x);
    }

    private static void printFind(long x) {

        //存放转换成三进制数的容器
        //位变化的容器
        ArrayList<String> Slist = new ArrayList<>();

        //先把数转换成三进制：
        long r;
        while (x != 0) {
            r = x % 3;
            Slist.add(r + "");
            x = x / 3;
        }


        //进行处理，把位上的2变为-1，进位（1 2 1 == 1 -1 -1 1），保证不重复，即没有2的情况
        int jw = 0;//进位
        for (int i = 0; i < Slist.size(); i++) {
            String iStr = Slist.get(i);//获得位上的数
            if (iStr.equals("0") || iStr.equals("1")) {
                if (jw == 0) {
                    //如果是0 1，且进位为0，不处理
                    continue;
                } else {
                    //如果有进位，0变1不进位，1变-1进一位
                    if (iStr.equals("0")) {
                        jw = 0;
                        Slist.set(i, "1");
                    } else {
                        Slist.set(i, "-1");
                    }
                }
            } else {
                //如果是2
                if (jw == 0) {
                    //没有进位，变-1，进一位
                    jw = 1;
                    Slist.set(i, "-1");
                } else {
                    //有进位，该位是0，进一位
                    Slist.set(i, "0");
                }

            }

        }
        //末项有进位的补充
        if (jw == 1) {
            Slist.add("1");
        }
        //转换成十进制表达式
        for (int i = 0; i < Slist.size(); i++) {
            if (Slist.get(i).equals("1")) {
                Slist.set(i, "+" + pow(3, i));

            } else if (Slist.get(i).equals("-1")) {
                Slist.set(i, "-" + pow(3, i));

            } else {
                Slist.set(i, "+0");
            }
        }

        //处理最开头的加号
        if (Slist.get(Slist.size() - 1).substring(0, 1).equals("+")) {
            String first = Slist.get(Slist.size() - 1);
            Slist.set(Slist.size() - 1, first.substring(1));
        }
        for (int i = Slist.size() - 1; i >= 0; i--) {
            //遇0不输出
            System.out.print(Slist.get(i).equals("+0") ? "" : Slist.get(i));
        }
    }

    private static long pow(int num, int r) {
        //快速幂算法
        if (num == 0) return 0;
        if (r == 0) return 1;
        int result = 1;
        int k = num;
        while (r != 0) {
            if ((r & 1) == 1) {
                result *= k;
            }
            k *= k;
            r >>>= 1;
        }
        return result;
    }
}
