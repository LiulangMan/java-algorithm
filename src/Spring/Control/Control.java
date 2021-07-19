package Spring.Control;

import Spring.Dao.Dao;
import Spring.Factory.BeanFactory;
import Spring.Server.Server;
import Spring.User.User;
import org.springframework.stereotype.Controller;

@Controller
public class Control {
    private Dao dao = (Dao) BeanFactory.ioc.get("userDao");
    private User user = (User) BeanFactory.ioc.get("userUser");
    private Server server = (Server) BeanFactory.ioc.get("userServer");

    public Control() {
    }

    public void queryUserByid(int id) {
        System.out.println(server.queryUserById(id));
    }

}
