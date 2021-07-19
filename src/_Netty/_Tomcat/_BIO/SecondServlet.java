package _Netty._Tomcat._BIO;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/14 16:15
 */
public class SecondServlet extends IServlet {
    @Override
    public void doPost(IRequest request, IResponse response) throws Exception {
        response.write("this is second servlet");
    }

    @Override
    public void doGet(IRequest request, IResponse response) throws Exception {
        this.doPost(request,response);
    }
}
