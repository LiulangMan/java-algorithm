package _算法._字符串专题;

public class _两字符字符集是否相同_不考虑数目 {
    public static void main(String[] args) {
        String s1 = "abcddddddef";
        String s2 = "aaaaabccccdeeef";
        System.out.println(check(s1,s2));
    }

    private static boolean check(String s1, String s2) {
        char[] helper = new char[256];
        for (int i = 0; i <s1.length() ; i++) {
            char c = s1.charAt(i);
            if (helper[c] == 0){
                helper[c] = 1;
            }
        }

        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            if (helper[c] == 1){
                helper[c] = 2;
            }
        }
        for (char c : helper) {
            if (c == 1)
                return false;
        }
        return true;
    }
}
