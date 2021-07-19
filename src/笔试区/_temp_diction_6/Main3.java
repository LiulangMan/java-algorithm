package 笔试区._temp_diction_6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Pop {
    public int val;
    public boolean visited = false;
    public ArrayList<Pop> linked = new ArrayList<>();//保存可连接

    public Pop(int val) {
        this.val = val;
    }

    public void add_link(Pop pop) {
        linked.add(pop);
    }

    public int linked_size() {
        return linked.size();
    }
}

public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        HashMap<Integer, Pop> map = new HashMap<>();

        //初始化
        for (int i = 0; i < n; i++) {
            map.put(i, new Pop(i));
        }
        //建立连接
        for (int i = 1; i < n; i++) {
            int j = sc.nextInt();
            map.get(i).add_link(map.get(j));//i j 连接
            map.get(j).add_link(map.get(i));//i j 连接
        }
        System.out.println(f(map, k, 0, 0) + 1);


    }

    private static int f(HashMap<Integer, Pop> map, int k, int from, int self) {
        if (k == 0) return 0;
        Pop start = map.get(self);//起点
        start.visited = true;//标记已经被遍历过
        int max = 0;//不能回走的最大数
        for (int i = 0; i < start.linked_size(); i++) {
            if (start.linked.get(i).val != from) {
                if (!start.linked.get(i).visited) {
                    //如果没访问过，+1
                    max = Math.max(max, 1 + f(map, k - 1, self, start.linked.get(i).val));
                    start.linked.get(i).visited = false;//回溯
                }
            }

        }

        if (is_End(from, map.get(self))) {
            //如果到了尽头，k依旧大于0 回溯
            max += f(map, k - 1, self, from);//回走

        }

        return max;
    }

    private static boolean is_End(int from, Pop pop) {
        for (int i = 0; i < pop.linked_size(); i++) {
            if (from != pop.linked.get(i).val) return false;
        }
        return true;
    }


}
