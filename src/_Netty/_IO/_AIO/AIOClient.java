package _Netty._IO._AIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/10 22:37
 */
public class AIOClient {
    private AsynchronousSocketChannel client;

    public AIOClient() throws Exception {
        client = AsynchronousSocketChannel.open();
    }

    public void client(String host, int port) throws Exception {
        client.connect(new InetSocketAddress(host, port), null, new CompletionHandler<Void, Object>() {
            @Override
            public void completed(Void result, Object attachment) {
                try {
                    client.write(ByteBuffer.wrap("这是一条测试数据".getBytes())).get();
                    System.out.println("已发送至服务器");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println(exc.toString());
            }
        });

        final ByteBuffer bb = ByteBuffer.allocate(1024);
        client.read(bb, null, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println("IO操作完成:" + result);
                System.out.println("获取反馈结果:" + new String(bb.array()));
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                exc.printStackTrace();
            }
        });

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        new AIOClient().client("127.0.0.1",8000);
    }
}
