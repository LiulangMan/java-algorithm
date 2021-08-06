package _AndroidFramework.Handler机制;

import java.util.LinkedList;

//消息
public final class Message {


    private static LinkedList<Message> cache;
    private static final int CACHE_SIZE = 30;

    static {
        cache = new LinkedList<>();
        for (int i = 0; i < CACHE_SIZE; i++) {
            cache.addLast(new Message());
        }
    }

    public String what;
    public Long when;
    public Runnable callback;
    public Long sendWhen;
    public Object arg1;
    public Object arg2;

    //是否可以处理当前消息？
    public boolean doHandleMessage() {
        return System.currentTimeMillis() >= sendWhen + when;
    }

    /**
     * 享元模式
     **/
    public static Message obtain() {
        if (cache.size() >= CACHE_SIZE) {
            return new Message();
        }

        return cache.removeFirst();
    }

    public static void addCache(Message message) {
        if (message == null) return;
        if (cache.size() >= CACHE_SIZE) return;

        message.when = null;
        message.sendWhen = null;
        message.what = null;
        message.callback = null;
        cache.addLast(message);
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
