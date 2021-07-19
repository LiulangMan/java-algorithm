package _设计模式._行为型._委派模式;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/1 16:43
 */

@SuppressWarnings("all")
public class Boss {
    private Leader leader = new Leader();

    public int acknowledgeTotal() {
        return leader.total();
    }

    public static void main(String[] args) {
        Boss boss = new Boss();
        System.out.println(boss.acknowledgeTotal());
    }


    static class Leader {
        public static Map<String, Integer> map = new HashMap<>();

        static {
            map.put("1", 1);
            map.put("2", 2);
            map.put("3", 3);
            map.put("4", 4);
            map.put("5", 5);
        }

        public int total() {
            int total = 0;
            for (Integer value : map.values()) {
                total += value;
            }
            return total;
        }
    }
}
