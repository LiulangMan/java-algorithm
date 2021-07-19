package _tmp;

import java.math.BigDecimal;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/16 14:45
 */
public class JustFunny {
    public static void main(String[] args) {
        BigDecimal decimal = new BigDecimal("1.222");
        System.out.println(decimal.pow(2).add(new BigDecimal("11.37")).multiply(BigDecimal.TEN));
    }
}
