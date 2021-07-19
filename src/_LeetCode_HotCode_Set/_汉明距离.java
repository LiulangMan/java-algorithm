package _LeetCode_HotCode_Set;

import org.junit.Test;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/29 15:52
 */
public class _汉明距离 {
    private int hammingDistance(int x, int y) {
        int result = x ^ y;
        int i = 0;
        while (result != 0) {
            if ((result & 1) == 1) {
                i++;
            }
            result >>>= 1;
        }

        return i;
    }

    @Test
    public void test(){
        System.out.println(hammingDistance(1,4));
    }
}
