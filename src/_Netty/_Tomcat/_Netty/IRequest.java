package _Netty._Tomcat._Netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/14 22:31
 */
public class IRequest {
    private ChannelHandlerContext ctx;
    private HttpRequest req;

    public IRequest(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public String getUrl() {
        return req.uri();
    }

    public String getMethod() {
        return req.method().name();
    }

    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(req.uri());
        return decoder.parameters();
    }

    public String getParameters(String name) {
        Map<String, List<String>> parameters = getParameters();
        List<String> param = parameters.get(name);
        if (param == null) {
            return null;
        } else {
            return param.get(0);
        }
    }
}
