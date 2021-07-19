package 笔试区._temp_diction2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Link {
    Link next;
    //int id;
    Link pre;
    int value;

    Link(Link next, Link pre, int value) {
        this.value = value;
        this.next = next;
        this.pre = pre;

    }

}

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        //ArrayList<Integer> list = new ArrayList<>();
        Map<Integer, Link> map = new HashMap<>();

        Link head = new Link(null, null, sc.nextInt());
        Link pre = head;
        map.put(0, head);
        for (int i = 1; i < n; i++) {
            //int m = sc.nextInt();
            Link node = new Link(null, pre, sc.nextInt());
            pre.next = node;
            pre = node;

            map.put(i, node);
        }

        if (k - 1 > 0 && k - 1 < n - 1) {
            Link target = map.get(k - 1);
            Link pre1 =  map.get(k - 2);
            Link next = map.get(k);
            pre1.next = next;
            next.pre = pre1;
        } else if (k - 1 == 0) {
            head = head.next;
        } else {
            map.get(k - 2).next = null;
        }

        Link t = head;
        while (t != null) {
            System.out.print(t.value + " ");
            t = t.next;
        }
    }
}
