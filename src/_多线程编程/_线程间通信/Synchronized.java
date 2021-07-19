package _多线程编程._线程间通信;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/5 21:46
 *
 * @Text: 保证多线程中只有一个线程执行同步块，线程a获取锁代表a开始写入消息，线程a释放锁代表a发送消息，
 *        线程b获取锁代表b接收a发送的消息，这即是线程间通信。volatile的可见性同样适合线程间通信.
 *        volatile:保证原子性和可见性，即强制从共享内存区读取数据，避免多线程间脏读
 */
public class Synchronized {

    public static void main(String[] args) {
        //对Synchronized.class对象加锁
        synchronized (Synchronized.class){
        }
        //静态同步方法，对Synchronized.class对象加锁
        m();
    }

    public static synchronized void m(){}

    //对实例加锁
    public synchronized void n(){}
}
