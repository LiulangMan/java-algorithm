package _网络编程._Demo;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


//inputStream  字节流
//inputStreamReader 字节流与字符流的中间地带
//bufferReader  字符流

public class _client_1 {
    public static void main(String[] args) throws Exception {

        String host = "localhost";
        int post = 8080;
        Socket socket = new Socket(host, post);

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello !!!server!!!".getBytes(StandardCharsets.UTF_8));

        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println("message from server:" + line);
        }

        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
