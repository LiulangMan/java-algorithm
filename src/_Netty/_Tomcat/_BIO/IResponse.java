package _Netty._Tomcat._BIO;

import java.io.OutputStream;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/14 16:03
 */
public class IResponse {
    private OutputStream out;

    public IResponse(OutputStream out) {
        this.out = out;
    }

    public void write(String s) throws Exception {
        //输出也要遵循HTTP
        //状态码200
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\n").append("Content-Type: text/html;\n")
                .append("\r\n").append(s);
        out.write(sb.toString().getBytes());
    }
}
