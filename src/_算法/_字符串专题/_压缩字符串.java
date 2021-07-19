package _算法._字符串专题;

public class _压缩字符串 {
    public static void main(String[] args) {
        String str = "aabbbccccddddddeffff";
        System.out.println(zip(str));
    }

    private static String zip(String str) {

        StringBuilder sb = new StringBuilder();
        char a = str.charAt(0);
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (a == str.charAt(i)) {
                count++;
            } else {
                sb.append(a).append(count);
                a = str.charAt(i);
                count = 1;
            }

        }
        //追加最后
        sb.append(a).append(count);

        if (sb.length() == str.length()) {
            return str;
        } else {
            return sb.toString();
        }
    }
}
