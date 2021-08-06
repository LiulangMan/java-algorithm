package _LeetCode_HotCode_Set;


/****
 *
 *给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * @Link：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {

        ListNode p = this;
        StringBuilder sb = new StringBuilder();
        while (p != null) {
            sb.append(p.val);
            sb.append("->");
            p = p.next;
        }

        return sb.toString();
    }
}

public class _两数相加 {

    public static void main(String[] args) {
        ListNode p1 = new ListNode(2);
        ListNode p2 = new ListNode(8);
        p2.next = new ListNode(9);
        p2.next.next = new ListNode(9);
        System.out.print(addTwoNumbers(p1, p2));

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {


        ListNode p1 = l1;
        ListNode p2 = l2;

        boolean jw = false;//进位计算

        ListNode head = null;
        ListNode p = head;

        while (p1 != null || p2 != null || jw) {
            int n1 = 0;
            int n2 = 0;
            if (p1 != null) n1 = p1.val;
            if (p2 != null) n2 = p2.val;
            int append = (n1 + n2) % 10 + (jw ? 1 : 0);
            jw = (n1 + n2 + (jw ? 1 : 0)) / 10 != 0;
            append %= 10;

            if (head == null) {
                head = new ListNode(append);
                p = head;
            } else {
                ListNode node = new ListNode(append);
                p.next = node;
                p = node;
            }

            if (p1 != null) p1 = p1.next;
            if (p2 != null) p2 = p2.next;
        }
        return head;
    }
}
