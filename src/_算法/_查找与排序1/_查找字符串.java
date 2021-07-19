package _算法._查找与排序1;

/***
 *
 *
 * 一排序好的字符串数组，其中有空串，编写一个方法indexof，找出非空字符串的索引
 *
 *
 *
 * ***/
public class _查找字符串 {
    public static void main(String[] args) {
        String[] arr = {"a","","ab","ac","","","","cd",""};
        String p = "cdf";
        int ans = indexof(arr,p);
        System.out.println(ans);
    }

    private static int indexof(String[] arr, String p) {
        int begin = 0;
        int end = arr.length-1;

        while (begin < end){
            int mid = begin + ((end - begin)>>1);
            while (arr[mid].equals("")&&mid<=end)mid++;
            if (arr[mid].compareTo(p)>0){
                end = mid - 1;
            }
            else if (arr[mid].compareTo(p)<0){
                begin = mid + 1;
            }
            else{
                return mid;
            }
        }
        return -1;
    }
}
