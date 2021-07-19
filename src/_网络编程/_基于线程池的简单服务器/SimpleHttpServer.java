package _网络编程._基于线程池的简单服务器;

import _多线程编程._线程池实例.DefaultThreadPool;
import _多线程编程._线程池实例.ThreadPool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/8 14:42
 */
public class SimpleHttpServer {
    //处理httpRequest的线程池
    private static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<>();
    //SimpleHttpServer 根路径
    private static String basePath;
    private static ServerSocket serverSocket;
    //服务器监听端口
    private static int port = 8080;

    public static void setPort(int port) {
        if (port <= 0) return;
        SimpleHttpServer.port = port;
    }

    public static void setBasePath(String basePath) {
        if (basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()) {
            SimpleHttpServer.basePath = basePath;
        }
    }

    //启动simpleHttpServer
    public static void start() throws Exception {
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        System.out.println("正在监听：" + port + "端口");
        while ((socket = serverSocket.accept()) != null) {
            //接收一个客户端的socket，生成一个HttpRequestHandler放入线程池执行
            threadPool.execute(new HttpRequestHandler(socket));
        }
        serverSocket.close();
    }

    static class HttpRequestHandler implements Runnable {
        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader reader = null;
            BufferedReader br = null;
            PrintWriter out = null;
            InputStream in = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                //由相对路径算出绝对路径
                String filePath = basePath + header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());
                //请求资源的后缀为jpg或者ico，则读出资源并输出
                if (filePath.endsWith("jpg") || filePath.endsWith("ico")) {
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i = 0;
                    while ((i = in.read()) != -1) {
                        baos.write(i);
                    }
                    byte[] array = baos.toByteArray();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: image/jpg");
                    out.println("Content-Length:" + array.length);
                    out.println("");
                    socket.getOutputStream().write(array, 0, array.length);
                } else {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: text/html;charset=UTF-8");
                    out.println("");
                }
            } catch (Exception e) {
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            } finally {
                close(br, in, reader, out, socket);
            }

        }

        private void close(Closeable... closeables) {
            if (closeables != null) {
                for (Closeable closeable : closeables) {
                    try {
                        closeable.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        SimpleHttpServer.start();
    }
}
