package _算法._查找与排序1;

/****
 * 递归问题的父问题和子问题要等价。
 *
 * 将问题划分成小问题，即先把N-1to1当作整体移动到辅助C，再将N移动到B（由于N最大，可认为B是空），那么下
 * 一子问题就是将N-1Tto1移动到B，A作为赋值数组，以此类推
 *
 *移动次数 2^n - 1
 * **/
public class _汉诺塔游戏 {
    public static void main(String[] args) {
        int N = 3;
        printhannotor(N,"A","B","C");

    }
    private static void printhannotor(int N,String from,String to,String help){
        if (N==1){
            System.out.println("move "+N+" from "+from+" to "+to+" help "+help);
            return;
        }
        printhannotor(N-1,from,help,to);//交换辅助
        System.out.println("move "+N+" from "+from+" to "+to+" help "+help);
        printhannotor(N-1,help,to,from);

    }
}
