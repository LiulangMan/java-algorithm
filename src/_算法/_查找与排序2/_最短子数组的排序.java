package _算法._查找与排序2;

/***
 * 给定一个无序数组，返回需要排序的子数组大小
 * 比如2 3 7 6 4 5
 * 子数组7 6 4 5  return 4
 *
 * **/
public class _最短子数组的排序 {
    public static void main(String[] args) {
        int[] arr = {2, 3, 7, 6, 5, 8, 5, 7, 9, 10, 15};
        int[] arr1 = {5, 4, 3, 2, 1};
        int[] arr2 = {1, 2, 3, 4, 5};
        int ans = find(arr2);
        System.out.println(ans);
    }

    private static int find(int[] arr) {
        int start = 0;
        int end = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            //找起点：第一个下折
            if (arr[i] > arr[i + 1]) {
                start = i;
                break;
            }
        }
        end = start;
        boolean lock = true;
        int max = arr[start];
        for (int j = start; j < arr.length; j++) {
            //找终点
            if (arr[j] >= max && lock) {
                end = j - 1;//预选择
                lock = false;//锁定，继续扫描
            }
            if ((j - 1 >= 0) && arr[j - 1] > arr[j]) {
                //出现了新折点
                if (max < arr[j - 1]) {
                    max = arr[j - 1];//更新最大值点
                }
                end = j;//保持指针更进
                lock = true;//解锁
            }
        }
        //System.out.println(start + " " + end);
        return end - start + 1;
    }

}
