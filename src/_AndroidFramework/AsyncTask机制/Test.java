package _AndroidFramework.AsyncTask机制;

import _AndroidFramework.Handler机制.Looper;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        Looper.prepareMain();

        AsyncTask<Integer, Integer, Integer> work = new AsyncTask<Integer, Integer, Integer>() {
            @Override
            protected void done(Integer[] result) {
                System.out.println(Thread.currentThread().getName() + "   任务已经完成，退出程序！");
                System.out.println(Arrays.toString(result));
                Looper.getMainLooper().quit();
            }

            @Override
            protected void doing(Integer[] progress) {
                System.out.println(Thread.currentThread().getName() + "   任务进行中......" + Arrays.toString(progress));
            }

            @Override
            protected Integer[] doInBackground(Integer[] integers) {
                System.out.println("开始任务" + Thread.currentThread().getName());
                for (int i = 0; i < 10; i++) {
                    publishProgress(new Integer[]{i});
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return new Integer[]{10000};
            }
        };

        work.execute();
        Looper.loop();
        System.out.println("程序结束---------------");
    }
}
