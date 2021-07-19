package _Netty._Tomcat._BIO;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/14 16:18
 *
 * @Text: 手写Tomcat
 */
public class ITomcat {
    private int port = 8080;
    private ServerSocket serverSocket;
    private Map<String, IServlet> servletMapping = new HashMap<>();
    private Properties webXml = new Properties();


    private void init() {
        try {
            //加载web.xml文件，同时初始化servletMapping
            String WEB_INF = this.getClass().getResource("").getPath();
            FileInputStream fin = new FileInputStream(WEB_INF + "web.properties");

            webXml.load(fin);

            for (Object key : webXml.keySet()) {
                String k = key.toString();
                if (k.endsWith(".url")) {
                    String servletName = k.replaceAll("\\.url$", "");
                    String url = webXml.getProperty(k);
                    String className = webXml.getProperty(servletName + ".className");
                    //单实例，多线程
                    IServlet obj = (IServlet) Class.forName(className).newInstance();
                    servletMapping.put(url, obj);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        init();

        try {

            serverSocket = new ServerSocket(this.port);
            System.out.println("Tomcat已启动，监听端口是：" + this.port);

            //等待用户请求
            while (true) {
                Socket client = serverSocket.accept();
                //HTTP请求，发送的数据就是有规律的字符串
                process(client);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void process(Socket client) throws Exception {
        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();

        IRequest request = new IRequest(is);
        IResponse response = new IResponse(os);

        //从协议内容中获得URL，把相应的servlet用反射进行实例化
        String url = request.getUrl();

        if (servletMapping.containsKey(url)) {
            servletMapping.get(url).service(request, response);
        } else {
            response.write("404 - Not Found");
        }

        os.flush();
        os.close();

        is.close();
        client.close();
    }

    public static void main(String[] args) {
        new ITomcat().start();
    }
}
