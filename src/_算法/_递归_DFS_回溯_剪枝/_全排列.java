package _算法._递归_DFS_回溯_剪枝;

import java.util.ArrayList;

public class _全排列 {
    private static char[] arr = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
    private static int count;

    public static void main(String[] args) {

//        findway(0);
//        System.out.println(count);


//        String src = "ABCDEFG";
//        ArrayList<String> ans2 = findway2(src);
//        for (int i = 0; i < ans2.size(); i++) {
//            System.out.println(ans2.get(i));
//        }
//        System.out.println(ans2.size());

//        findway3("",arr,1);

    }

    private static void findway3(String prefix,char[] arr,int k){
        //前缀法，返回有字典序的全排列
        if (prefix.length() == arr.length){
            count++;
            if (count == k){
                //输出字典序第k的排列

                System.out.println(prefix);
                System.exit(0);//退出程序
            }
        }

//        if (pre.length() == arr.length) {
//            //do something
//            if (!pre.equals("") && !list.contains(pre)) list.add(pre);
//            return;
//        }

        for (int i = 0; i <arr.length ; i++) {
            char c = arr[i];
            //这个字符可用，在prefix出现次数<在arr中出现次数

//            if (count(prefix.toCharArray(),c)<count(arr,c)){
//                findway3(prefix+c,arr,k);
//            }
            if (count(prefix,c) < count(arr,c)){
                findway3(prefix+c,arr,k);
            }
        }

    }
    private static int count(char[] arr,char c){
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == c)cnt++;
        }
        return cnt;
    }

    private static <T> int count(T t,char c){
        //泛型方法
        int cnt = 0;
        String src = t.toString();
        for (int i = 0; i < src.length(); i++) {
            if (src.charAt(i) == c)cnt++;
        }
        return cnt;
    }

    private static ArrayList<String> findway2(String src) {
        //迭代:增量法
        ArrayList<String> ans = new ArrayList<>();

        ans.add(src.charAt(0) + "");

        for (int i = 1; i < arr.length; i++) {

            char c = src.charAt(i);
            ArrayList<String> new_ans = new ArrayList<>();

            for (String eOflist : ans) {
                //加前面
                String new_str = c + eOflist;
                new_ans.add(new_str);
                //加后面
                new_str = eOflist + c;
                new_ans.add(new_str);

                //加中间

                for (int j = 1; j < eOflist.length(); j++) {
                    new_str = eOflist.substring(0, j) + c + eOflist.substring(j);
                    new_ans.add(new_str);
                }
            }

            ans = new_ans;
        }

        return ans;
    }

    private static void findway(int cur) {
        //递归：交换法
        if (cur == arr.length - 1) {
            count++;
            for (char e : arr) {
                System.out.print(e);
            }
            System.out.println();
        }

        for (int i = cur; i < arr.length; i++) {
            char temp = arr[cur];
            arr[cur] = arr[i];
            arr[i] = temp;

            //移交下一层
            findway(cur + 1);

            //回溯
            temp = arr[cur];
            arr[cur] = arr[i];
            arr[i] = temp;
        }
    }
}
