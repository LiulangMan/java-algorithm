package _Netty._Tomcat._Netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/14 20:39
 * @Text: 基于Netty重构Tomcat
 */
public class ITomcat {

    private int port = 8080;
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
        //Netty封装了NIO的Reactor模型，Boss，Worker
        //Boss线程
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        //Worker线程
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //创建对象
            ServerBootstrap server = new ServerBootstrap();
            //配置参数
            //链路式编程
            server.group(boosGroup, workerGroup)
                    //主线程处理类，看到这种写法，提层使用反射机制
                    .channel(NioServerSocketChannel.class)
                    //子线程处理类，Handler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel client) throws Exception {
                            //无锁化串行编程
                            //Netty对HTTP的封装，对顺序有要求
                            //HttpResponse，双向链表Inbound Outbound
                            client.pipeline().addLast(new HttpResponseEncoder());
                            client.pipeline().addLast(new HttpRequestDecoder());
                            //业务处理逻辑
                            client.pipeline().addLast(new ITomcatHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

            //启动服务器
            ChannelFuture f = server.bind(port).sync();
            System.out.println("Tomcat 已经启动，监听端口:" + port);
            f.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public class ITomcatHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof HttpRequest) {
                System.out.println("收到GET请求......");
                HttpRequest req = (HttpRequest) msg;
                //转交给我们自己的request实现
                IRequest request = new IRequest(ctx, req);
                //转交给我们自己的response
                IResponse response = new IResponse(ctx, req);
                //实际业务处理
                String url = request.getUrl();
                if (servletMapping.containsKey(url)) {
                    servletMapping.get(url).service(request, response);
                } else {
                    response.write("404 - Not found");
                }

            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        }
    }


    public static void main(String[] args) {
        new ITomcat().start();
    }
}