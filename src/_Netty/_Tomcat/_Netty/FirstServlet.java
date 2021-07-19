package _Netty._Tomcat._Netty;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/14 16:13
 */
public class FirstServlet extends IServlet {
    @Override
    public void doPost(IRequest request, IResponse response) throws Exception {
        response.write("this is first servlet");
    }

    @Override
    public void doGet(IRequest request, IResponse response) throws Exception {
        this.doPost(request,response);
    }
}
