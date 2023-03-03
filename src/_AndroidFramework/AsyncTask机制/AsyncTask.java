package _AndroidFramework.AsyncTask机制;

import _AndroidFramework.Handler机制.Handler;
import _AndroidFramework.Handler机制.Message;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AsyncTask<Params, Progress, Result> {

    private Handler mainHandler;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    private Params[] params;
    private Result[] results;
    private Progress[] progresses;


    public AsyncTask() {
        //必须在主线程创建
        this.mainHandler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        done(results);
                        executorService.shutdown();
                        break;
                    case 1:
                        doing((Progress[]) message.arg1);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    public void setParams(Params[] params) {
        this.params = params;
    }

    protected abstract void done(Result[] result);

    protected abstract void doing(Progress[] progress);

    protected abstract Result[] doInBackground(Params[] params);


    protected void execute() {

        executorService.execute(() -> {
            results = doInBackground(params);
            //任务完成后，发信息给handler
            Message message = Message.obtain();
            message.what = 0;
            message.when = 0L;
            message.arg1 = results;
            mainHandler.sendMessage(message);
        });
    }

    protected final void publishProgress(Progress[] progress) {
        //在doInBackground中调用
        Message message = Message.obtain();
        message.what = 1;
        message.when = 0L;
        message.arg1 = progress;
        System.out.println("更新状态----" + Thread.currentThread().getName() + "  " + Arrays.toString(progress));
        this.mainHandler.sendMessage(message);
    }
}
