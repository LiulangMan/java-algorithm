package _算法._位算法;

//可以采用暴力破解，或者key-value破解
//以下算法仅是练习位运算
//如下：
//两个相同的二进制数做不进位加法结果为0
//10个相同的十进制数做不进位加法结果为0
//k个相同的k进制数做不进位加法结果也为0

import static java.lang.Math.pow;

//因此，把所有数转为k进制做不进位加法，就能找出唯一的一个数
public class _出现k次与出现一次 {
    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 5, 5, 5, 7, 7, 7, 8,8,8,76, 9, 9, 9, 4, 4, 4};
        int k = 3;
        int maxlen = 0;
        String[] str = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            //翻转让低位对齐
            str[i] = new StringBuilder(Integer.toString(arr[i], k)).reverse().toString();
            if (str[i].length() > maxlen) maxlen = str[i].length();
        }

        //补0
        for (int i = 0; i < arr.length; i++) {
            if (str[i].length() < maxlen) {
                int l = str[i].length();
                for (int j = 0; j < maxlen -l; j++) {
                    str[i] = str[i] + "0";
                }
            }
        }
        //做不进位加法
        int p = 0;//记录
        char[] r = new char[maxlen];
        for (int j = 0; j < maxlen; j++) {
            for (int i = 0; i < str.length; i++) {
                p = str[i].toCharArray()[j] - '0' + p;
                if (p >= k) p = p - k;
            }
            r[maxlen - j - 1] = (char) (p + '0');
            p = 0;
        }

        //r即是结果,转回十进制
        int result = 0;
        for (int i = 0; i < r.length; i++) {
            result += (r[i] - '0') * pow(k, r.length - i - 1);
        }
        System.out.println(result);

    }
}
