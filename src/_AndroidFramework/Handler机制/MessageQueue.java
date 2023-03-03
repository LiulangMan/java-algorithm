package _AndroidFramework.Handler机制;


//优先级队列
public class MessageQueue {


    //队列大小
    private int size;

    //队列头
    private Node head;

    //队列尾
    private Node tail;

    private volatile boolean quit = false;


    public MessageQueue() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    void quite() {
        quit = true;
    }

    /**
     * 维持的有序列表节点
     **/
    private static class Node {
        Node pre;
        Node next;
        Message message;

        public Node(Message message) {
            this.message = message;
        }
    }


    private boolean insertTail(Node node) {

        if (this.head == null) {
            this.head = this.tail = node;
            return true;
        }

        //顺序插入
        Node p = head;
        while (p != null) {
            if (p.message.when > node.message.when) {
                break;
            }
            p = p.next;
        }
        if (p == head) {
            //头部
            p.pre = node;
            node.next = p;
            head = node;
            return true;
        } else if (p == null) {
            //尾部
            tail.next = node;
            node.pre = tail;
            tail = node;
            return true;
        } else {
            //中间
            Node pre = p.pre;
            pre.next = node;
            node.next = p;
            node.pre = pre;
            p.pre = node;
            return true;
        }
    }

    private Node removeFromHead() {

        if (head == null) return null;

        Node p = head;
        Node newHead = head.next;
        if (newHead == null) {
            head = tail = null;
        } else {
            newHead.pre = null;
            head = newHead;
        }
        return p;
    }

    private void recycleAllMessage() {
        Node pt = head;
        while (pt != null) {
            pt.message.recycle();
            pt = pt.next;
        }

        head = tail = null;
        size = 0;
    }


    /**
     * 消息api
     **/

    public void offer(Message message) {
        if (this.insertTail(new Node(message))) {
            this.size++;
        }
    }

    Message next() {
        for (; ; ) {
            if (quit) {
                recycleAllMessage();
                return null;
            }

            if (head == null || head.message.when < System.currentTimeMillis()) {
                continue;
            }

            return poll();
        }
    }

    Message poll() {
        Node node = this.removeFromHead();

        if (node == null) {
            return null;
        }
        this.size--;
        return node.message;
    }
}
