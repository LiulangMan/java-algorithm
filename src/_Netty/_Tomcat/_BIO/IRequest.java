package _Netty._Tomcat._BIO;

import java.io.InputStream;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/14 16:03
 */
public class IRequest {
    private String method;
    private String url;

    public IRequest(InputStream in) {
        try {
            //获取Http内容
            String content = "";
            byte[] buff = new byte[1024];
            int len = 0;
            if ((len = in.read(buff)) > 0) {
                content = new String(buff, 0, len);
            }

            String line = content.split("\\n")[0];
            String[] arr = line.split("\\s");
            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
