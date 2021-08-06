package _AndroidFramework.Handler机制;


//手写一个android handler
public abstract class Handler {

    private MessageQueue mMessageQueue;
    private Message mMessage;
    private Looper mLooper;


    public Handler() {
        Looper looper = Looper.looperThreadLocal.get();
        if (looper == null) {
            throw new Error("looper not exist where current thread !");
        }
        this.mLooper = looper;
        this.mMessageQueue = new MessageQueue();

        //创建完毕后再绑定looper
        looper.setHandler(this);
    }

    public Handler(Looper looper) {
        if (looper == null) {
            throw new NullPointerException("");
        }
        this.mLooper = looper;
        this.mMessageQueue = new MessageQueue();
        //创建完毕后再绑定looper
        looper.setHandler(this);
    }

    public void post(Runnable runnable) {
        this.mMessage = Message.obtain();
        mMessage.callback = runnable;
        mMessage.when = 0L;
        mMessage.what = null;
        this.sendMessage(mMessage);
    }

    public void sendMessage(Message message) {
        message.sendWhen = System.currentTimeMillis();
        this.mMessageQueue.offer(message);
    }

    public abstract void handleMessage(Message message);


    public MessageQueue getMessageQueue() {
        return mMessageQueue;
    }

    public Looper getLooper() {
        return mLooper;
    }

    public void setLooper(Looper mLooper) {
        this.mLooper = mLooper;
    }
}
