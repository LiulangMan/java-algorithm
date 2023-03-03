package _AndroidFramework.Handler机制;

import com.sun.istack.internal.Nullable;

//循环读取者
public class Looper {

    final static ThreadLocal<Looper> sLooperThreadLocal = new ThreadLocal<>();
    private static Looper sMainLooper;
    private final MessageQueue mMessageQueue = new MessageQueue();
    private Looper() {
    }

    @Nullable
    public static Looper getLooper() {
        return sLooperThreadLocal.get();
    }

    public static Looper getMainLooper() {
        return sMainLooper;
    }

    public static void prepare() {
        if (sLooperThreadLocal.get() != null) {
            throw new Error("current thread already has prepared!");
        }

        sLooperThreadLocal.set(new Looper());
    }

    public static void prepareMain() {
        prepare();
        sMainLooper = getLooper();
    }

    public static void loop() {
        //do something
        Looper looper = sLooperThreadLocal.get();
        if (looper == null) {
            throw new RuntimeException("please use prepare() to create looper!");
        }

        while (true) {
            Message message = looper.mMessageQueue.next();
            if (message == null) {
                //返回null，表示退出
                return;
            }
            message = looper.mMessageQueue.poll();
            message.target.dispatchMessage(message);

            //处理完消息后，放入缓存池
            message.recycle();
        }
    }

    public void quit() {
        mMessageQueue.quite();
    }

    MessageQueue getMessageQueue() {
        return mMessageQueue;
    }
}
