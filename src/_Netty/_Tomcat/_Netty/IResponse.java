package _Netty._Tomcat._Netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/14 22:31
 */
public class IResponse {
    //SocketChannel的封装
    private ChannelHandlerContext ctx;
    private HttpRequest req;

    public IResponse(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public void write(String out) throws Exception {
        try {
            if (out == null || out.length() == 0) {
                return;
            }

            //设置Http及其请求
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(out.getBytes(StandardCharsets.UTF_8))
            );

            response.headers().set("Content-Type", "text/html;");
            ctx.write(response);

        } finally {
            ctx.flush();
            ctx.close();
        }
    }
}
