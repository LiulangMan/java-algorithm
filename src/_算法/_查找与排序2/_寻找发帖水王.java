package _算法._查找与排序2;


/***
 * 寻找数组中，出现次数超过数组长度一半的元素
 *
 *
 * ***/
public class _寻找发帖水王 {
    public static void main(String[] args) {
        int[] arr = {1,5,3,5,4,5,4,5,6,5,8,5,5};
        int ans = f(arr);
        int ans2 = f2(arr); //变体，恰好只出现一半
        System.out.println(ans);
    }

    private static int f2(int[] arr) {
        //arr长度必然为偶数，仅多一个计数，记录最后一个元素重复次数，若等于一半，它就是水王，否则，就是condition
        int condition = arr[0]; // 定起点
        int times = 1;//出现次数
        int lasttimes = 0;//与最后元素的计数比较
        for (int i = 1; i < arr.length; i++) {
            //多了这个操作
            if (arr[i] == arr[arr.length - 1])lasttimes++;

            if (times == 0){ //两两消除为0，就更新新元
                condition = arr[i];
                times = 1;
                continue;
            }
            if(condition == arr[i]){
                times ++;
            }
            else times --;
        }
        if (lasttimes == arr.length/2)return arr[arr.length - 1];
        return condition;
    }

    private static int f(int[] arr) {
        //经典算法：顺序消除法
        int condition = arr[0]; // 定起点
        int times = 1;//出现次数
        for (int i = 1; i < arr.length; i++) {
            if (times == 0){ //两两消除为0，就更新新元
                condition = arr[i];
                times = 1;
                continue;
            }
            if(condition == arr[i]){
                times ++;
            }
            else times --;
        }
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (condition == arr[i]) cnt++;
        }
        return cnt > arr.length / 2 ? condition : 0;
    }
}
