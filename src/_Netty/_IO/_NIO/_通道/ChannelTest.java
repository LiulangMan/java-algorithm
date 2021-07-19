package _Netty._IO._NIO._通道;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/11 16:40
 */
public class ChannelTest {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("src/_Netty/resources/example.txt");
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //把数据从channel写入buffer
        channel.read(buffer);

        //反转读取写入到buffer的数据
        buffer.flip();

        while (buffer.remaining() > 0) {
            System.out.print((char) buffer.get());
        }

    }
}
