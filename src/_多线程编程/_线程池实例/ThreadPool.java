package _多线程编程._线程池实例;

public interface ThreadPool<Job extends Runnable> {
    //执行一个Job，这个Job需要实现Runnable
    void execute(Job job);

    //关闭线程池
    void shutdown();

    //增加工作者线程
    void addWorker(int num);

    //减少工作者线程
    void removeWorker(int num);

    //得到正在执行等待的任务数量
    int getJobSize();
}
