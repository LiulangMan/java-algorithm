package _AndroidFramework.Handler机制;

import java.util.LinkedList;

//消息
public final class Message {

    private final static LinkedList<Message> sMessageCachePools = new LinkedList<>();
    private static final int CACHE_SIZE = 30;

    public int what;
    public long when;
    public Runnable callback;
    public long sendWhen;
    public Object arg1;
    public Object arg2;

    public Handler target;

    //是否可以处理当前消息

    /**
     * 享元模式
     **/
    public static Message obtain() {
        synchronized (sMessageCachePools) {
            if (sMessageCachePools.size() >= CACHE_SIZE || sMessageCachePools.isEmpty()) {
                return new Message();
            }

            return sMessageCachePools.removeFirst();
        }
    }

    public void recycle() {
        this.when = 0;
        this.sendWhen = 0;
        this.what = 0;
        this.callback = null;
        this.target = null;

        synchronized (sMessageCachePools) {
            if (sMessageCachePools.size() >= CACHE_SIZE) return;
            sMessageCachePools.addLast(this);
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                "what='" + what + '\'' +
                ", when=" + when +
                ", sendWhen=" + sendWhen +
                ", arg1=" + arg1 +
                ", arg2=" + arg2 +
                '}';
    }
}
