package _AndroidFramework.Handler机制;


import com.sun.istack.internal.NotNull;

//手写一个android handler
public abstract class Handler {

    @NotNull
    private final Looper mLooper;


    public Handler() {
        Looper looper = Looper.sLooperThreadLocal.get();
        if (looper == null) {
            throw new Error("looper not exist where current thread !");
        }
        this.mLooper = looper;
    }

    public Handler(Looper looper) {
        if (looper == null) {
            throw new NullPointerException("");
        }
        this.mLooper = looper;
    }

    public void post(Runnable runnable) {
        Message mMessage = Message.obtain();
        mMessage.callback = runnable;
        mMessage.when = 0L;
        mMessage.what = 0;
        this.sendMessage(mMessage);
    }

    public void postDelay(Runnable runnable, long delay) {
        Message mMessage = Message.obtain();
        mMessage.callback = runnable;
        mMessage.when = delay + System.currentTimeMillis();
        mMessage.what = 0;
        this.sendMessage(mMessage);
    }

    public void sendMessage(Message message) {
        message.target = this;
        this.mLooper.getMessageQueue().offer(message);
    }

    public void sendMessageDelay(Message message, long delay) {
        message.target = this;
        message.when = delay + System.currentTimeMillis();
        this.mLooper.getMessageQueue().offer(message);
    }

    protected abstract void handleMessage(Message message);

    void dispatchMessage(Message message) {
        if (message.callback != null) {
            message.callback.run();
        } else {
            message.target.handleMessage(message);
        }
    }
}
