package _算法._字符串专题;

//将空格替换成 %20
public class _替换空格 {
    public static void main(String[] args) {
        String str = "I love li xue lian";
        System.out.println(replace_space(str.toCharArray()));
    }

    private static String replace_space(char[] charArr) {
        int count = charArr.length;
        for (char c : charArr) {
            if (c == ' ') {
                count += 2;
            }
        }

        char[] new_char_array = new char[count];
        int p1 = charArr.length - 1;
        int p2 = count - 1;
        while (p1 >= 0) {
            if (charArr[p1] == ' ') {
                new_char_array[p2--] = '0';
                new_char_array[p2--] = '2';
                new_char_array[p2--] = '%';
            } else {
                new_char_array[p2--] = charArr[p1];
            }
            p1--;
        }
        return new String(new_char_array);
        //JDK工具：
        //return new String(charArr).replaceAll(" ","%20");
    }
}
