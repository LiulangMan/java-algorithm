package Spring.Dao;

import Spring.Factory.BeanFactory;
import Spring.User.User;


//数据层
public class Dao {
    User user = (User) BeanFactory.ioc.get("userUser");

    public Dao() {
    }
}
