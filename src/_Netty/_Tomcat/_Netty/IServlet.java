package _Netty._Tomcat._Netty;



/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/14 16:00
 */
public abstract class IServlet {
    public void service(IRequest request,IResponse response) throws Exception{
        //有service方法决定是调用doGet还是doPost
        if ("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request,response);
        }else {
            doPost(request,response);
        }
    }

    public abstract void doPost(IRequest request, IResponse response) throws Exception;

    public abstract void doGet(IRequest request, IResponse response) throws Exception;
}
