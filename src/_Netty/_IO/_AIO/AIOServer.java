package _Netty._IO._AIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/10 22:16
 * @Text: AIO服务器
 */

public class AIOServer {
    private final int port;

    public AIOServer(int port) {
        this.port = port;
        listen();
    }

    private void listen() {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            AsynchronousChannelGroup threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
            final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(threadGroup);
            server.bind(new InetSocketAddress(port));
            System.out.println("服务器已启动，监听端口" + port);

            server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {

                final ByteBuffer buffer = ByteBuffer.allocate(1024);

                @Override
                public void completed(AsynchronousSocketChannel result, Object attachment) {
                    System.out.println("IO操作成功，开始获取数据");
                    try {
                        buffer.clear();
                        result.read(buffer).get();
                        System.out.println("收到数据:" + new String(buffer.array()));
                        buffer.flip();
                        result.write(buffer);//写入数据给发送端
                        buffer.flip();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    } finally {
                        try {
                            result.close();
                            server.accept(null, this);
                        } catch (IOException e) {
                            System.out.println(e.toString());
                        }

                    }

                    System.out.println("操作完成");

                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("IO操作失败:" + exc.toString());
                }
            });

            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //Test
    public static void main(String[] args) {
        int port = 8000;
        new AIOServer(port);
    }


}
