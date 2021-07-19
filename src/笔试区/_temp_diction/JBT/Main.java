package 笔试区._temp_diction.JBT;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String[] srr = s.split(",");
        int[] arr = new int[srr.length];
        for(int i = 0;i<arr.length;i++){
            arr[i] = Integer.parseInt(srr[i]);
        }

        f(arr);

    }

    public static void f(int[] arr){
        int max = Integer.MIN_VALUE;

        int p = 0;
        for (int value : arr) {
            p += value;
            if (p < value) {
                p = value;
            }
            max = Math.max(max, p);
        }

        System.out.println(max);

    }
}