package _网络编程._Demo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class _Server_1 {
    public static void main(String[] args) throws Exception {
        //String host = "15.15.15.15";
        int post = 9999;

        ServerSocket server = new ServerSocket(post);

        Socket socket = server.accept();

        InputStream inputStream = socket.getInputStream();
        BufferedReader Reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));


        String line;
        while ((line = Reader.readLine()) != null) {
            System.out.println("message from client:" + line);
        }


        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello!!!!client!!!".getBytes(StandardCharsets.UTF_8));


        socket.close();
        server.close();
        outputStream.close();
        inputStream.close();
        Reader.close();
    }
}
