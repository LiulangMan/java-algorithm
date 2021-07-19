package _多线程编程._并发;


class Example {
    //同步锁 synchronized
    synchronized public void f() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "    " + i);
            if (Thread.interrupted()) break;
        }
    }
}

public class Mythread extends Thread {
    public static void main(String[] args) throws InterruptedException {
        Example example = new Example();
        Mythread t1 = new Mythread(example);
        Mythread t2 = new Mythread(example);
        t1.start();
        t2.start();
        t1.interrupt();
    }

    private Example t;

    Mythread(Example t) {
        this.t = t;
    }

    @Override
    public void run() {
        this.t.f();
    }
}

