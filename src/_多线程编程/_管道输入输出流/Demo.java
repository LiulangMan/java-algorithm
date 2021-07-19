package _多线程编程._管道输入输出流;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/6 13:21
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        //将输入流和输出流进行连接，否则抛出IOException
        out.connect(in);
        Thread printThread = new Thread(new Print(in), "PrintThread");
        printThread.start();
        int receive = 0;

        try {
            while ((receive = System.in.read()) != -1) {
                out.write(receive);
            }
        } finally {
            out.close();
        }

    }

    static class Print implements Runnable {

        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;
            try {
                while ((receive = in.read()) != -1) {
                    System.out.print((char) receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
