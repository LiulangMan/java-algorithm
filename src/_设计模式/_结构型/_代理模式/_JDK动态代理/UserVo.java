package _设计模式._结构型._代理模式._JDK动态代理;

public class UserVo implements User {
    @Override
    public void walk() {
        System.out.println("walk!");
    }
}
