package _算法._字符串专题;

public class _翻转字符串 {
    public static void main(String[] args) {
        String str = "abcdefg";
        System.out.println(reverse_string(str));
    }

    private static String reverse_string(String str) {
        char[] arr = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            arr[i] = str.charAt(str.length() - 1 - i);
        }
        return new String(arr);
//      JDK工具：
//      StringBuffer sb = new StringBuffer(str);
//      return sb.reverse().toString();
    }
}
