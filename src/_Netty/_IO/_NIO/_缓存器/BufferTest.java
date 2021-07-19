package _Netty._IO._NIO._缓存器;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/11 15:18
 * @Text: 在NIO中，所有读取操作都在buffer中完成
 */
public class BufferTest {

    @Test
    public void intBuffer() {
        //分配新的int缓冲区，参数为缓存区容量
        IntBuffer intBuffer = IntBuffer.allocate(8);

        for (int i = 0; i < intBuffer.capacity(); i++) {
            int j = 2 * (i + 1);
            intBuffer.put(j);
        }

        //重设此缓存区，将限制位置设置为当前位置，然后将当前位置设置为0
        intBuffer.flip();
        //查看在当前位置和限制位置之间是否有元素
        while (intBuffer.hasRemaining()) {
            int j = intBuffer.get();
            System.out.println("j = " + j);
        }

    }

    @Test
    public void floatBuffer() {
        //other buffer look like intBuffer
    }

    @Test
    public void buffer() throws Exception {
        FileInputStream fin = new FileInputStream("src/_Netty/resources/example2.txt");
        //获取通道
        FileChannel fc = fin.getChannel();
        //申请byte[10]
        //等价于：
        //byte[] bytes = new byte[10];
        //ByteBuffer buffer = ByteBuffer.wrap(bytes);
        ByteBuffer buffer = ByteBuffer.allocate(10);
        output("初始化", buffer);

        //先读取到buffer中
        fc.read(buffer);
        output("read()", buffer);

        //准备操作之前，先锁定操作范围
        buffer.flip();//保存锁定的范围是已经读入的数据
        output("flip()", buffer);

        while (buffer.remaining() > 0) {
            byte b = buffer.get();
            System.out.print((char) b);
        }
        output("get()", buffer);

        //清空缓存
        buffer.clear();
        output("clear()", buffer);

        //关闭通道
        fin.close();
    }

    private void output(String step, Buffer buffer) {
        System.out.println("step = " + step);
        System.out.println("buffer.capacity = " + buffer.capacity());
        System.out.println("buffer.position() = " + buffer.position());
        System.out.println("buffer.limit() = " + buffer.limit());
        System.out.println();
    }

    @Test
    public void bufferSlice() {
        //缓冲区分片
        ByteBuffer buffer = ByteBuffer.allocate(10);

        //写入数据
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }
        //创建子缓冲区
        buffer.position(3);
        buffer.limit(7);
        ByteBuffer slice = buffer.slice();

        //改变缓冲区中的内容
        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *= 10;
            slice.put(i, b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.remaining() > 0) {
            System.out.println(buffer.get());
        }
    }

    @Test
    public void onlyReadBuffer() {
        //只读缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(10);

        //写入数据
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }
        //创建只读缓冲区
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        buffer.position(3);
        buffer.limit(7);

        //改变缓冲区中的内容
        for (int i = buffer.position(); i < buffer.limit(); i++) {
            byte b = buffer.get(i);
            b *= 10;
            buffer.put(i, b);
        }

        readOnlyBuffer.position(0);
        readOnlyBuffer.limit(buffer.capacity());

        //只读缓冲区的内容随之改变
        while (readOnlyBuffer.remaining() > 0) {
            System.out.println(readOnlyBuffer.get());
        }
    }

    @Test
    public void directBuffer(){
        //分配直接缓存（直接调用IO，没有中间层），使用无差别
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
    }

    @Test
    public void mapBuffer() throws Exception{
        //内存文件映射
        RandomAccessFile fileInputStream = new RandomAccessFile("src/_Netty/resources/example3.txt","rw");
        FileChannel channel = fileInputStream.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
        map.put(0,(byte) 97);
        map.put(1023,(byte) 122);
        map.position(0);
        map.limit(1024);

        channel.close();
    }
}
