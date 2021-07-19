package _算法._查找与排序2;


import java.util.Arrays;
import java.util.Comparator;

/***
 * 组合数组，使其成为最小的组合数
 *  如 32 321 3
 *  结果：321323
 *
 * ***/
public class _特殊排序 {
    public static void main(String[] args) {
        Integer[] arr = {3, 32, 321};
        int ans = find(arr);
        System.out.println(ans);
    }

    private static int find(Integer[] arr) {
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //自定义比较规则
                String s1 = o1 + "" + o2;
                String s2 = o2 + "" + o1;
                return s1.compareTo(s2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        return Integer.parseInt(sb.toString());
    }
}
