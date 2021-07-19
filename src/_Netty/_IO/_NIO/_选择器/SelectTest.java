package _Netty._IO._NIO._选择器;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/11 16:12
 */
public class SelectTest {
    private int port = 8000;
    private Selector selector = getSelector();
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    /*
     * 注册事件
     * */
    private Selector getSelector() {
        try {
            //创建Selector对象
            Selector sel = Selector.open();

            //创建可选择通道，并配置非阻塞模式
            ServerSocketChannel server = ServerSocketChannel.open();
            server.configureBlocking(false);

            //绑定通道到指定端口
            ServerSocket socket = server.socket();
            InetSocketAddress address = new InetSocketAddress(port);
            socket.bind(address);

            //像Selector注册感兴趣的事件
            server.register(sel, SelectionKey.OP_ACCEPT);
            return sel;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /*开始监听*/
    public void listen() {
        System.out.println("开始监听：" + port);

        try {
            while (true) {
                //该调用会阻塞，直到有一个事件发生
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                for (SelectionKey key : keys) {
                    process(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("监听失败");
        }

    }

    /*根据不同的事件处理*/
    private void process(SelectionKey key) {
        try {
            //接收请求
            if (key.isAcceptable()) {
                ServerSocketChannel server = (ServerSocketChannel) key.channel();
                SocketChannel channel = server.accept();
                channel.configureBlocking(false);
                channel.register(selector, SelectionKey.OP_READ);
            }

            //读数据
            else if (key.isReadable()) {
                SocketChannel channel = (SocketChannel) key.channel();
                int len = channel.read(buffer);
                if (len > 0) {
                    buffer.flip();
                    String content = new String(buffer.array(), 0, len);
                    SelectionKey writer = channel.register(selector, SelectionKey.OP_WRITE);
                    writer.attach(content);
                } else {
                    channel.close();
                }
                buffer.clear();
            }

            //写数据
            else if (key.isWritable()) {
                SocketChannel channel = (SocketChannel) key.channel();
                String content = (String) key.attachment();
                ByteBuffer wrap = ByteBuffer.wrap(("输出内容:" + content).getBytes());
                if (wrap != null) {
                    channel.write(wrap);
                } else {
                    channel.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SelectTest().listen();
    }
}
