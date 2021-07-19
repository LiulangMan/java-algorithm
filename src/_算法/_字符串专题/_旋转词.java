package _算法._字符串专题;

/***
 *
 * 如果a是b的旋转词，必定有b+b 包含 a
 *
 *
 * ***/
public class _旋转词 {

    public static void main(String[] args) {
        String s1 = "bcde";
        String s2 = "bcdea";
        System.out.println(is_rotate(s1,s2));
    }

    private static boolean is_rotate(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        sb.append(s2).append(s2);
        return sb.toString().contains(s1);
    }
}
