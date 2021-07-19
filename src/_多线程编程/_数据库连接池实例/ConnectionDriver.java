package _多线程编程._数据库连接池实例;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/6 21:31
 */
public class ConnectionDriver {
    static class ConnectionHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("commit")) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            return null;
        }

    }

    static Connection createConnection() {
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
                new Class[]{Connection.class}, new ConnectionHandler());
    }
}
